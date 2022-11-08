package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class ItemDetailsPage {

    WebDriver driver;

    public ItemDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String URL = "/p/";
    public String ADAUGA_IN_COS_BTN = "//a[@class='add2cart danger button full icon fa-basket']";
    public String WISHLIST_BTN = "//a[@class='4u plain align-center add2list-btn']//i[@class='fa fa-heart']";
    public String ITEM_TITLE = "section[id='book-main'] span[itemprop='name']";
    public String ITEM_AUTHOR = "a[itemprop='author']";
    public String READ_MORE_BTN = "//a[@class='read-more']";
    public String READ_LESS_BTN = "//a[@class='read-less']";
    public String ITEM_DESCRIPTION_EXPANDED = "//main//br[last()]";
    public String RATE_STARS = ".stars>a";
    public String AUTHOR_LINK = "//a[@itemprop='author']";


    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL);
    }

    public boolean addToCart() {
        driver.findElement(By.xpath(ADAUGA_IN_COS_BTN)).click();
        WebDriverWait wait = new WebDriverWait(driver, 6);
        return wait.until(ExpectedConditions.urlContains("cart/added/"));
    }

    public WishlistPage addToWishlist() {
        driver.findElement(By.xpath(WISHLIST_BTN)).click();
        return new WishlistPage(driver);
    }

    public String getItemTitle() {
        return driver.findElement(By.cssSelector(ITEM_TITLE)).getText().toLowerCase();
    }

    public String getItemAuthor() {
        return driver.findElement(By.cssSelector(ITEM_AUTHOR)).getText().toLowerCase();
    }

    public boolean readMore() throws InterruptedException {
        driver.findElement(By.xpath(READ_MORE_BTN)).click();
        Thread.sleep(2000);
        if (driver.findElement(By.xpath(ITEM_DESCRIPTION_EXPANDED)).isDisplayed()) {
           return true;
        }
        return false;
    }

    public boolean readLess() {
        WebElement element = driver.findElement(By.xpath(READ_LESS_BTN));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);

        if (driver.findElement(By.xpath(ITEM_DESCRIPTION_EXPANDED)).isDisplayed()) {
            return false;
        }
        return true;
    }

    public int rate(int rating) {
        List<WebElement> stars = driver.findElements(By.cssSelector(RATE_STARS));
        stars.get(rating).click();
        driver.navigate().refresh();
        List<WebElement> ratingGiven = driver.findElements(By.cssSelector(".stars>a[class$='fa fa-star gold']"));
        System.out.println(ratingGiven.size());
        return ratingGiven.size();
    }

    public String seeAllBooksByAuthor() throws InterruptedException {
        String authorName = driver.findElement(By.xpath("//a[contains(@itemprop,'author')]")).getText();
        driver.findElement(By.xpath(AUTHOR_LINK)).click();
        Thread.sleep(3000);
        return driver.findElement(By.xpath("//h1[contains(text(),'" + authorName + "')]")).getText();
    }

}

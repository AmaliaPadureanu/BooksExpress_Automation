package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class ItemDetailsPage {

    WebDriver driver;

    public ItemDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    private String URL = "/p/";
    private String ADAUGA_IN_COS_BTN = "//a[@class='add2cart danger button full icon fa-basket']";
    private String WISHLIST_BTN = "//a[@class='4u plain align-center add2list-btn']//i[@class='fa fa-heart']";
    private String ITEM_TITLE = "section[id='book-main'] span[itemprop='name']";
    private String ITEM_AUTHOR = "a[itemprop='author']";
    private String READ_MORE_BTN = "//a[@class='read-more']";
    private String READ_LESS_BTN = "//a[@class='read-less']";
    private String ITEM_DESCRIPTION_EXPANDED = "//main//br[last()]";
    private String RATE_STARS = ".stars>a";
    private String AUTHOR_LINK = "//a[@itemprop='author']";
    private String PUBLISHER_LINK = "//a[@itemprop='publisher']";
    private String ACCEPT_COOKIES_BTN = "//a[contains(text(),'Accept cookie-uri')]";
    private String REVIEW_BTN = "//a[normalize-space()='Am citit!']";
    private String TEXT_INPUT = "//textarea[@id='review']";
    private String RATE_STARS_POPUP = "//div[@class='box lightbox']//a[@class='fa fa-star']";
    private String POST_ANONYMOUSLY_CHECKBOX = "//input[@id='post_anon'][@class='small']";
    private String SAVE_REVIEW_BTN = "//button[@id='saveReview']";
    private String REVIEWS = "//section[@id='product-reviews']//div[@class='read-text serif']//h4[contains(.,'a dat nota: ')]";
    private String MODIFY_REVIEW_BTN = "//a[@id='modify-review']";
    private String REMOVE_REVIEW_BTN = "//a[@id='remove-review']";

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

    public String seeAllByAuthor() throws InterruptedException {
        String authorName = driver.findElement(By.xpath(AUTHOR_LINK)).getText();
        driver.findElement(By.xpath(AUTHOR_LINK)).click();
        Thread.sleep(3000);
        return driver.findElement(By.xpath("//h1[contains(text(),'" + authorName + "')]")).getText();
    }

    public String seeAllFromPublisher() throws InterruptedException {
        String publisherName = driver.findElement(By.xpath(PUBLISHER_LINK)).getText();
        driver.findElement(By.xpath(ACCEPT_COOKIES_BTN)).click();
        driver.findElement(By.xpath(PUBLISHER_LINK)).click();
        Thread.sleep(3000);
        return driver.findElement(By.xpath("//h1[contains(text(),'" + publisherName + "')]")).getText();
    }

    public int getNrOfReviews() {
        return driver.findElements(By.xpath(REVIEWS)).size();
    }

    public void writeReview(Integer rating, Boolean postAnonymous, String reviewText) {
        driver.findElement(By.xpath(ACCEPT_COOKIES_BTN)).click();
        driver.findElement(By.xpath(REVIEW_BTN)).click();
        driver.switchTo().activeElement();

        List<WebElement> stars = driver.findElements(By.xpath(RATE_STARS_POPUP));
        stars.get(rating).click();

        if (postAnonymous) {
            WebElement element = driver.findElement(By.xpath(POST_ANONYMOUSLY_CHECKBOX));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
        }

        driver.findElement(By.xpath(TEXT_INPUT)).sendKeys(reviewText);
        driver.findElement(By.xpath(SAVE_REVIEW_BTN)).click();
    }

}

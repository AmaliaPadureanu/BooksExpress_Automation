package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Locale;

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
    public String ITEM_DESCRIPTION = "//main[@class='read-text serif']//p[contains(text(),'-')]";

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
        return driver.findElement(By.cssSelector(ITEM_TITLE)).getText().toLowerCase(Locale.ROOT);
    }

    public String getItemAuthor() {
        return driver.findElement(By.cssSelector(ITEM_AUTHOR)).getText().toLowerCase(Locale.ROOT);
    }

    public void readMore() {
        driver.findElement(By.xpath(READ_MORE_BTN)).click();
    }

    public void readLess() {
        driver.findElement(By.xpath(READ_LESS_BTN)).click();
    }
    

}

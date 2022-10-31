package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Locale;

public class WishlistPage {

    WebDriver driver;

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
    }

    public String LISTS_BTN = "//span[normalize-space()='Liste']";
    public String WISHLIST_BTN = "//a[@href='/user/wishlist']";
    public String ITEM_TITLE = "div[class='cart-details'] h4 a";
    public String REMOVE_BTN = "//a[@class='color-theme-5 remove-item']";
    public String ITEMS_IN_LIST = "#list-items>li";

    public void navigateToWishlist() {
        driver.findElement(By.xpath(LISTS_BTN)).click();
        WebElement element = driver.findElement(By.xpath(WISHLIST_BTN));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    public String getItemTitle() {
        return driver.findElement(By.cssSelector(ITEM_TITLE)).getText().toLowerCase(Locale.ROOT);
    }

    public void removeItem() {
        driver.findElement(By.xpath(REMOVE_BTN)).click();
    }

    public boolean isEmpty() {
        List<WebElement> items = driver.findElements(By.cssSelector(ITEMS_IN_LIST));
        if (items.size() != 0) {
            return false;
        } else return true;
    }
}

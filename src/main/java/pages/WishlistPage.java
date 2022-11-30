package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class WishlistPage {

    WebDriver driver;

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
    }

    private String LISTS_BTN = "//span[normalize-space()='Liste']";
    private String ITEM_TITLE = "div[class='cart-details'] h4 a";
    private String REMOVE_BTN = "//a[@class='color-theme-5 remove-item']";
    private String ITEMS_IN_LIST = "#list-items>li";
    private String ADD_TO_CART = "button[type='button']";

    public List<String> getItemsTitle() {
        List<String> itemsInWishlist = new ArrayList<>();

        for (WebElement element : driver.findElements(By.cssSelector("div[class='cart-details'] h4 a"))) {
            itemsInWishlist.add(element.getText().substring(3).toLowerCase());
        }

        return itemsInWishlist;
    }

    public void removeItem() {
        driver.findElement(By.xpath(REMOVE_BTN)).click();
    }

    public boolean isEmpty() {
        List<WebElement> items = driver.findElements(By.cssSelector(ITEMS_IN_LIST));
        if (items.size() != 0) {
            return false;
        }
        return true;
    }

    public void addToCart() {
        driver.findElement(By.cssSelector(ADD_TO_CART)).click();
    }

}

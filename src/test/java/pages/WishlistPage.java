package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class WishlistPage extends BasePage {

    private By ITEM_TITLE = By.cssSelector("div[class='cart-details'] h4 a");
    private By REMOVE_BTN = By.xpath("//a[@class='color-theme-5 remove-item']");
    private By ITEMS_IN_LIST = By.cssSelector("#list-items>li");
    private By ADD_TO_CART = By.cssSelector("button[type='button']");
    private By ITEMS_IN_WISHLIST = By.cssSelector("div[class='cart-details'] h4 a");

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getItemsTitle() {
        List<String> itemsInWishlist = new ArrayList<>();

        for (WebElement element : findAll(ITEMS_IN_WISHLIST)) {
            itemsInWishlist.add(element.getText().substring(3));
        }

        return itemsInWishlist;
    }

    public void removeItem() {
        click(REMOVE_BTN);
    }

    public boolean isEmpty() {
        List<WebElement> items = findAll(ITEMS_IN_LIST);
        if (items.size() != 0) {
            return false;
        }
        return true;
    }

    public void addToCart() {
        click(ADD_TO_CART);
    }
}

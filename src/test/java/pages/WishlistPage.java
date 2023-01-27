package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WishlistPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.CSS, using = "div[class='cart-details'] h4 a")
    private WebElement itemTitle;
    @FindBy(how = How.XPATH, using = "//a[@class='color-theme-5 remove-item']")
    private WebElement removeButton;
    @FindBy(how = How.CSS, using = "#list-items>li")
    private List<WebElement> itemsInList;
    @FindBy(how = How.CSS, using = "button[type='button']")
    private WebElement addToCartButton;
    @FindBy(how = How.CSS, using = "div[class='cart-details'] h4 a")
    private List<WebElement> itemsInWishlist;

    public WishlistPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public List<String> getItemsTitle() {
        List<String> items = new ArrayList<>();

        for (WebElement element : itemsInWishlist) {
            items.add(element.getText().substring(3));
        }

        return items;
    }

    public void removeItem() {
        removeButton.click();
    }

    public boolean isEmpty() {

        if (itemsInList.size() != 0) {
            return false;
        }
        return true;
    }

    public void addToCart() {
        addToCartButton.click();
    }
}

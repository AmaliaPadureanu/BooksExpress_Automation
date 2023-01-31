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

    @FindBy(how = How.CSS, using = "#list-items > li > div > div:first-child  > div > h4 > a")
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
        List<String> titles = new ArrayList<>();

        for (WebElement item : itemsInList) {
            titles.add(item.getText().substring(3).split(" ")[0]);
        }

        return titles;
    }

    public void removeItem(int item) {
        itemsInList.get(item).findElement(By.xpath("//a[@class='color-theme-5 remove-item']")).click();
    }

    public int getNumberOfItemsInWishlist() {
        return itemsInWishlist.size();
    }

    public void addToCart() {
        addToCartButton.click();
    }
}

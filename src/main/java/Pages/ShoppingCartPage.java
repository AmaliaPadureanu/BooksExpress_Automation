package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShoppingCartPage {

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    public String VEZI_COSUL_BTN = "//a[@class='button special full']";
    public String COS_BTN = "//span[contains(text(),'Coș')]";
    public String STERGE_BTN = "//a[@class='color-theme-5 cart-remove-item']";
    public String QUANTITY_FIELD = "//input[contains(@type,'number')]";


    public String navigateToCart() {
        driver.findElement(By.xpath(COS_BTN)).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath(VEZI_COSUL_BTN)).click();
        return driver.getCurrentUrl();
    }

    public String removeFromCart() {
        driver.findElement(By.xpath(STERGE_BTN)).click();
        return driver.getCurrentUrl();
    }

    public void getCartItems() {
        List<WebElement> itemsInCart = driver.findElements(By.xpath("//li[@class='row 25%']"));
        System.out.println(itemsInCart.get(0).getText());
    }

    public int changeQuantity(int qty) {
        WebElement quantity = driver.findElement(By.xpath(QUANTITY_FIELD));
        quantity.sendKeys(Keys.DELETE);
        quantity.sendKeys(String.valueOf(qty));
        quantity.sendKeys(Keys.ENTER);
        int value = Integer.valueOf(driver.findElement(By.xpath(QUANTITY_FIELD)).getAttribute("value"));
        return value;
    }


}
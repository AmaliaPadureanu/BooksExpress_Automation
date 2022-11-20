package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShoppingCartPage {

    public WebDriver driver;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    private String VEZI_COSUL_BTN = "//a[@class='button special full']";
    private String COS_BTN = "//span[contains(text(),'Coș')]";
    private String STERGE_BTN = "//a[@class='color-theme-5 cart-remove-item']";
    private String QUANTITY_FIELD = "//input[contains(@type,'number')]";


    public String navigateToCart() {
        driver.findElement(By.xpath(COS_BTN)).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath(VEZI_COSUL_BTN)).click();
        return driver.getCurrentUrl();
    }

    public int removeFromCart() {
        driver.findElement(By.xpath(STERGE_BTN)).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> itemsInCart = driver.findElements(By.xpath("//li[@class='row 25%']"));
        return itemsInCart.size();
    }

    public int getNoOfCartItems() {
        List<WebElement> itemsInCart = driver.findElements(By.xpath("//li[@class='row 25%']"));
        return itemsInCart.size();
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
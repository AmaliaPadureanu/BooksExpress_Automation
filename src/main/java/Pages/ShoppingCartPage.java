package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage {

    public WebDriver driver;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    private String STERGE_BTN = "//a[@class='color-theme-5 cart-remove-item']";
    private String QUANTITY_FIELD = "//input[contains(@type,'number')]";

    public int removeFromCart() throws InterruptedException {
        driver.findElement(By.xpath(STERGE_BTN)).click();
        Thread.sleep(3000);
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
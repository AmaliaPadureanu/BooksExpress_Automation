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

    public List<WebElement> getItemsInCart() {
        return driver.findElements(By.cssSelector("#cart-items > li"));
    }

    public double formatPrice(Integer price) {
        char[] chars = String.valueOf(price).toCharArray();
        StringBuilder integerPart = new StringBuilder();
        StringBuilder decimalPart = new StringBuilder();

        for (int i = 0; i < chars.length - 2; i++) {
            integerPart.append(chars[i]);
        }

        decimalPart.append(chars[chars.length - 2]);
        decimalPart.append(chars[chars.length - 1]);

        double finalNumber = Double.valueOf(integerPart + "." + decimalPart);
        return finalNumber;
    }
}
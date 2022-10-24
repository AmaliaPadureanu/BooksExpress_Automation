package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class ShoppingCartPage {

    public ShoppingCartPage (WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    public String expectedURL = "https://www.books-express.ro/cart";

    public String emptyCart = "//h2[contains(text(),'Coșul de cumpăraturi este gol')]";
    public String VEZI_COSUL_BTN = "//a[@class='button special full']";
    public String COS_BTN = "//span[contains(text(),'Coș')]";
    public String EMPTY_CART_MESSAGE = "//b[contains(text(),'Coșul de cumpăraturi este gol. Navigați prin categ')]";
    public String EXPECTED_TEXT = "Coșul de cumpăraturi este gol. Navigați prin categorii sau folosiți bara de căutare pentru a găsi produsele dorite.";

    public boolean navigateToCart() {
        driver.findElement(By.xpath(COS_BTN)).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath(VEZI_COSUL_BTN)).click();
        return driver.getCurrentUrl().contains(expectedURL);
    }

    public boolean cartIsEmpty() {
        driver.findElement(By.xpath(COS_BTN)).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath(VEZI_COSUL_BTN)).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String cartText = driver.findElement(By.xpath(EMPTY_CART_MESSAGE)).getText();

        return cartText.equals(EXPECTED_TEXT);
    }




}

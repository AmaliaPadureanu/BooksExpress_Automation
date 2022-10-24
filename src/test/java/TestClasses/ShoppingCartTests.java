package TestClasses;

import Pages.LoginPage;
import Pages.ShoppingCartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ShoppingCartTests {

    WebDriver driver;
    String baseURL;

    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Amalia/IdeaProjects/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        Thread.sleep(1000);
        baseURL = "https://www.books-express.ro/";
        driver.get(baseURL);
    }

    @Test
    public void isLoginPageOpen() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.navigateToCart();
        Assert.assertTrue(shoppingCartPage.navigateToCart());
    }

    @Test
    public void emptyCartTest() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.cartIsEmpty();
        Assert.assertTrue(shoppingCartPage.cartIsEmpty());
    }


}

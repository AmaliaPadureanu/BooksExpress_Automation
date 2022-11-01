package TestClasses;

import Pages.ItemDetailsPage;
import Pages.ResultsPage;
import Pages.SearchPage;
import Pages.ShoppingCartPage;
import base.BaseTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ShoppingCartTests extends BaseTest {

    String CART_LINK = "https://www.books-express.ro/cart";
    String EMPTY_CART_LINK = "cart#";

    @BeforeClass
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Amalia/IdeaProjects/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        Thread.sleep(1000);
        baseURL = "https://www.books-express.ro/";
        driver.get(baseURL);
    }

    @Test
    public void addToCartTest() {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("George Martin");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.addToCart());
    }

    @Test
    public void navigateToCartTest() {
        shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.navigateToCart();
        Assert.assertTrue(shoppingCartPage.navigateToCart().equals(CART_LINK));
    }

    @Test
    public void removeFromCartTest() {
        shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.removeFromCart().contains(EMPTY_CART_LINK));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

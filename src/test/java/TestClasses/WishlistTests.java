package TestClasses;

import Pages.LoginPage;
import Pages.ResultsPage;
import Pages.SearchPage;
import Pages.WishlistPage;
import base.BaseTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class WishlistTests extends BaseTest {

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/Amalia/IdeaProjects/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        baseURL = "https://www.books-express.ro/";
        driver.get(baseURL);
    }

    @Test
    public void addToWishlist() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(email, password);
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("the secret history");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        String title = itemDetailsPage.getItemTitle().substring(3, 10);
        wishlistPage = itemDetailsPage.addToWishlist();
        Thread.sleep(6000);
        wishlistPage.navigateToWishlist();
        Thread.sleep(3000);
        Assert.assertTrue(wishlistPage.getItemTitle().contains(title));
    }

    @Test
    public void removeFromWishlist() throws InterruptedException {
        wishlistPage.removeItem();
        Thread.sleep(3000);
        Assert.assertTrue(wishlistPage.isEmpty());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}

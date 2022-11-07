package base;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;
    protected String baseURL;
    protected ContactPage contactPage;
    protected LoginPage loginPage;
    protected NavigationPage navigationPage;
    protected SearchPage searchPage;
    protected ShoppingCartPage shoppingCartPage;
    protected SignInPage signInPage;
    protected WishlistPage wishlistPage;
    protected ItemDetailsPage itemDetailsPage;
    protected UserDetailsPage userDetailsPage;

    public String email = "automationtesting630@gmail.com";
    public String password = "QAtest123";

    @BeforeClass
    @Parameters({"browser"})
    public void commonSetup(String browser) {
        driver = WebDriverFactory.getInstance().getDriver(browser);
        baseURL = "https://www.books-express.ro/";
        driver.get(baseURL);
    }

    @AfterClass
    public void commonTearDown() {
        WebDriverFactory.getInstance().quitDriver();
    }
}

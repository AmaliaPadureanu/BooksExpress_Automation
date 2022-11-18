package base;

import Pages.*;
import Utils.Constants;
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
    protected NavigationHistoryPage navigationHistoryPage;
    protected ListsPage listsPage;

    @BeforeClass
    @Parameters({"browser"})
    public void commonSetup(String browser) {
        driver = WebDriverFactory.getInstance().getDriver(browser);
        baseURL = Constants.BASE_URL;
        driver.get(baseURL);
    }

    @AfterClass
    public void commonTearDown() {
        WebDriverFactory.getInstance().quitDriver();
    }

    public void login() {
        loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.logInWith(Constants.EMAIL, Constants.PASSWORD);
    }

    public void logout() {
        loginPage = new LoginPage(driver);
        loginPage.logout();
    }
}

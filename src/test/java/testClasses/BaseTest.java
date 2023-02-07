package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.*;
import utils.*;

public class BaseTest {

    public WebDriver driver;

    //String browser = BrowserUtils.getBrowserFromEnvironmentVariables("autoBrowser");
    String config = ConstantUtils.CONFIG_FILE;
    String browser = GenericUtils.getBrowserFromConfig(config);
    String baseURL = GenericUtils.getBaseURL(config);
    String dbHostname, dbPort, dbUser, dbPassword, dbSchema;
    ContactPage contactPage;
    LoginPage loginPage;
    NavigationPage navigationPage;
    SearchPage searchPage;
    ShoppingCartPage shoppingCartPage;
    SignInPage signInPage;
    WishlistPage wishlistPage;
    ItemDetailsPage itemDetailsPage;
    UserDetailsPage userDetailsPage;
    NavigationHistoryPage navigationHistoryPage;
    ListsPage listsPage;
    SearchResultsPage searchResultsPage;

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        //driver = BrowserUtils.getBrowser(BrowserTypes.CHROME).getDriver();
        driver = BrowserUtils.getBrowser(browser);
        driver.get(baseURL);
        driver.findElement(By.linkText("Accept cookie-uri")).click();
        dbHostname = GenericUtils.getDbHostnameFromConfig(config);
        dbPort = GenericUtils.getDbPortFromConfig(config);
        dbUser = GenericUtils.getDbUserFromConfig(config);
        dbPassword = GenericUtils.getDbPasswordFromConfig(config);
        dbSchema = GenericUtils.getDbSchemaFromConfig(config);
        ExtentTestManager.startTest("test", "test");
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driver.quit();
    }

    public void login() {
        navigationPage = new NavigationPage(driver);
        loginPage = navigationPage.navigateToLogin();
        loginPage.logInWith(ConstantUtils.EMAIL, ConstantUtils.PASSWORD);
        WaitUtils.waitForUrlToBe(driver, "https://www.books-express.ro/", 10);
    }

    public void logout() {
        navigationPage = new NavigationPage(driver);
        loginPage = navigationPage.navigateToLogin();
        loginPage.logout();
    }
}

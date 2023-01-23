package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.*;
import utils.BrowserUtils;
import utils.ConstantUtils;
import utils.Constants;
import utils.GenericUtils;

public class BaseTest {

    public WebDriver driver;

    //String browser = BrowserUtils.getBrowserFromEnvironmentVariables("autoBrowser");
    String config = ConstantUtils.CONFIG_FILE;
    String browser = GenericUtils.getBrowserFromConfig(config);
    //String baseURL = "http://demo-store.seleniumacademy.com";
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

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        //driver = BrowserUtils.getBrowser(BrowserTypes.CHROME).getDriver();
        driver = BrowserUtils.getBrowser(browser);
        //driver.manage().window().maximize();
        driver.get(baseURL);
        driver.findElement(By.linkText("Accept cookie-uri")).click();
        dbHostname = GenericUtils.getDbHostnameFromConfig(config);
        dbPort = GenericUtils.getDbPortFromConfig(config);
        dbUser = GenericUtils.getDbUserFromConfig(config);
        dbPassword = GenericUtils.getDbPasswordFromConfig(config);
        dbSchema = GenericUtils.getDbSchemaFromConfig(config);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driver.quit();
    }

//    public void login() {
//        loginPage = new LoginPage(driver);
//        loginPage.open();
//        loginPage.logInWith(Constants.EMAIL, Constants.PASSWORD);
//    }
//
//    public void logout() {
//        loginPage = new LoginPage(driver);
//        loginPage.logout();
//    }
}

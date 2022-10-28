package base;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected String baseURL;
    protected ContactPage contactPage;
    protected LoginPage loginPage;
    protected NavigationPage navigationPage;
    protected SearchPage searchPage;
    protected ShoppingCartPage shoppingCartPage;
    protected SignInPage signInPage;

    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Amalia/IdeaProjects/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        baseURL = "https://www.books-express.ro/";
        driver.get(baseURL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}

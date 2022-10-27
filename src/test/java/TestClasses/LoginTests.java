package TestClasses;

import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests {

    WebDriver driver;
    String baseURL;
    String email = "automationtesting630@gmail.com";
    String password = "QAtest123";
    LoginPage loginPage;
    public String USER_INFO = "//a[normalize-space()='Info']";

    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Amalia/IdeaProjects/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        Thread.sleep(1000);
        baseURL = "https://www.books-express.ro/";
        driver.get(baseURL);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(email, password);
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath(USER_INFO)).isEnabled());
    }

    @Test
    public void open() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        Assert.assertTrue(loginPage.isOpen());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

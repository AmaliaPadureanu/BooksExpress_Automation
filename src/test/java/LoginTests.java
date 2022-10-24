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


    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Amalia/IdeaProjects/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        Thread.sleep(1000);
        baseURL = "https://www.books-express.ro/";
        driver.get(baseURL);

    }

    @Test
    public void testLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.logInWith(email, password);
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//a[normalize-space()='Info']")).isEnabled());
    }

    @Test
    public void isLoginPageOpen() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isOpen());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

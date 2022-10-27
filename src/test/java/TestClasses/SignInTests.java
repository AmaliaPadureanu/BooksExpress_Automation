package TestClasses;

import Pages.LoginPage;
import Pages.SignInPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignInTests {
    WebDriver driver;
    String baseURL;
    String email = "testemail6@test.com";
    String prenume = "Test";
    String nume = "Test";
    String parola = "testpass00";
    String afterSignInURL = "user/details";

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/Amalia/IdeaProjects/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        baseURL = "https://www.books-express.ro/";
        driver.get(baseURL);
    }

    @Test
    public void testSignIn() {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.open();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        signInPage.signInWith(email, prenume, nume, parola);
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        Assert.assertTrue(driver.getCurrentUrl().contains(afterSignInURL));
    }

    @Test
    public void open() {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.open();
        Assert.assertTrue(signInPage.isOpen());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

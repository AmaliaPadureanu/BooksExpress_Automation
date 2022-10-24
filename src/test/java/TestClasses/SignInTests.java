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

public class SignInTests {
    WebDriver driver;
    String baseURL;
    String email = "testemail@test.com";
    String prenume = "Test";
    String nume = "Test";
    String parola = "testpass00";
    String afterSignInURL = "https://www.books-express.ro/user/details";

    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Amalia/IdeaProjects/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        Thread.sleep(1000);
        baseURL = "https://www.books-express.ro/";
        driver.get(baseURL);

    }

    @Test
    public void testSignIn() throws InterruptedException {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.open();
        signInPage.signInWith(email, prenume, nume, parola);
        Thread.sleep(3000);
        Assert.assertTrue(driver.getCurrentUrl().equals(afterSignInURL));
    }

    @Test
    public void isSignInPageOpen() {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.open();
        Assert.assertTrue(signInPage.isOpen());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

package TestClasses;

import Pages.LoginPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    String email = "automationtesting630@gmail.com";
    String password = "QAtest123";
    public String USER_INFO = "//a[normalize-space()='Info']";

    @Test
    public void testLogin() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(email, password);
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath(USER_INFO)).isEnabled());
    }

    @Test
    public void open() {
        loginPage = new LoginPage(driver);
        loginPage.login();
        Assert.assertTrue(loginPage.isOpen());
    }
}

package TestClasses;

import Pages.LoginPage;
import Utils.Constants;
import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    public String USER_INFO = "//a[normalize-space()='Info']";

    @Test
    public void loginWithTest() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(Constants.EMAIL, Constants.PASSWORD);
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath(USER_INFO)).isEnabled());
    }

    @Test
    @Ignore
    public void openLoginPageTest() {
        loginPage = new LoginPage(driver);
        loginPage.login();
        Assert.assertTrue(loginPage.isOpen());
    }

    @Test
    public void logoutTest() {
        loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.logout(), 2);
    }
}

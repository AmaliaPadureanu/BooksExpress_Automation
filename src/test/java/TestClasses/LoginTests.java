package TestClasses;

import Pages.LoginPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    public String USER_INFO = "//a[normalize-space()='Info']";

    @DataProvider
    public Object[][] loginDataProvider() {
        return new Object[][] {
                {"nsmithtest@gmail.com", "testpass123"},
                {"mjonestest@gmail.com", "passtest321"},
                {"joestest@gmail.com", "qatestpass99"},
                {"mattjtest@gmail.com", "testqaqa60"}
        };
    }

    @Test (dataProvider = "loginDataProvider")
    public void loginWithTest(String email, String password) throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(email, password);
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath(USER_INFO)).isEnabled());
    }

    @Test (enabled = false)
    public void openLoginPageTest() {
        loginPage = new LoginPage(driver);
        loginPage.login();
        Assert.assertTrue(loginPage.isOpen());
    }

    @Test (enabled = false)
    public void logoutTest() {
        loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.logout(), 2);
    }

    @AfterMethod
    public void logout() {
        loginPage = new LoginPage(driver);
        loginPage.logout();
    }
}

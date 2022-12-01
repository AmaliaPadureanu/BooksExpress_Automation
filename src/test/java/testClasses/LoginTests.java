package testClasses;

import pages.LoginPage;
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
    public void loginWithTest(String email, String password) {
        loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.getPageTitle().contains("Intră în cont"));
        loginPage.logInWith(email, password);
        Assert.assertTrue(driver.findElement(By.xpath(USER_INFO)).isEnabled());
    }

    @AfterMethod
    public void logout() {
        loginPage = new LoginPage(driver);
        loginPage.logout();
    }
}

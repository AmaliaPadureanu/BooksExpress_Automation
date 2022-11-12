package TestClasses;

import Pages.LoginPage;
import Pages.SignInPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignInTests extends BaseTest {

    String afterSignInURL = "https://www.books-express.ro/user/details";

    @DataProvider
    public Object[][] signInDataProvider() {
        return new Object[][] {
                {"nsmithtest@gmail.com", "Nicole", "Smith", "testpass123"},
                {"mjonestest@gmail.com", "Marry", "Jones", "passtest321"},
                {"joestest@gmail.com", "Joe", "Smith", "qatestpass99"},
                {"mattjtest@gmail.com", "Matt", "Jones", "testqaqa60"}
        };
    }

    @Test (dataProvider = "signInDataProvider")
    public void testSignIn(String email, String firstname, String lastname, String password) throws InterruptedException {
        signInPage = new SignInPage(driver);
        signInPage.open();
        signInPage.signInWith(email, firstname, lastname, password);
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().equals(afterSignInURL));
    }

    @Test(enabled = false)
    public void open() {
        signInPage = new SignInPage(driver);
        signInPage.open();
        Assert.assertTrue(signInPage.isOpen());
    }

    @AfterMethod
    public void cleanup() {
        loginPage = new LoginPage(driver);
        loginPage.logout();
    }
}

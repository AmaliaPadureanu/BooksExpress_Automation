package testClasses;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.NavigationPage;
import utils.ConstantUtils;
import utils.SeleniumUtils;
import utils.WaitUtils;

public class LoginTests extends BaseTest {

    @DataProvider
    public Object[][] positiveLoginDataProvider() {
        return new Object[][]{
                {"nsmithtest@gmail.com", "testpass123"},
                {"mjonestest@gmail.com", "passtest321"},
                {"joestest@gmail.com", "qatestpass99"},
                {"mattjtest@gmail.com", "testqaqa60"}
        };
    }

    @DataProvider
    public Object[][] invalidLoginDataProvider() {
        return new Object[][]{
                {ConstantUtils.EMAIL, "thisisaninvalidpassword", "Parola este incorectă pentru acest cont", "wrongPasswordError"},
                {ConstantUtils.EMAIL, "", "Te rugăm să introduci parola.", "emptyPasswordError"}
        };
    }

    @Test (dataProvider = "positiveLoginDataProvider")
    public void loginWithPositiveDataTest(String email, String password) {
        navigationPage = new NavigationPage(driver);
        loginPage = navigationPage.navigateToLogin();
        Assert.assertTrue(loginPage.getPageTitle().contains("Intră în cont"));
        loginPage.logInWith(email, password);
        WaitUtils.waitForUrlToBe(driver, "https://www.books-express.ro/", 10);
        Assert.assertTrue(loginPage.getInfo());
        loginPage.logout();
    }

    @Test
    public void loginWithWrongEmailTest() {
        navigationPage = new NavigationPage(driver);
        loginPage = navigationPage.navigateToLogin();
        Assert.assertTrue(loginPage.getPageTitle().contains("Intră în cont"));
        loginPage.enterEmail("notaregisteredemail@gmail.com");
        WaitUtils.waitForUrlToBe(driver, "https://www.books-express.ro/register", 10);
        Assert.assertEquals(SeleniumUtils.getCurrentURL(driver), "https://www.books-express.ro/register");
        SeleniumUtils.navigateBack(driver);
        SeleniumUtils.navigateBack(driver);
    }

    @Test (dataProvider = "invalidLoginDataProvider")
    public void loginWithInvalidPasswordTest(String email, String password, String passwordError, String errorType) {
        navigationPage = new NavigationPage(driver);
        loginPage = navigationPage.navigateToLogin();
        Assert.assertTrue(loginPage.getPageTitle().contains("Intră în cont"));
        loginPage.logInWith(email, password);
        Assert.assertTrue(loginPage.checkPasswordError(passwordError, errorType));
        SeleniumUtils.navigateBack(driver);
    }

    @Test
    public void resetPasswordTest() {
        navigationPage = new NavigationPage(driver);
        loginPage = navigationPage.navigateToLogin();
        Assert.assertTrue(loginPage.getPageTitle().contains("Intră în cont"));
        Assert.assertEquals(loginPage.resetPassword("nsmithtest@gmail.com"), "Instructiunile de resetare a parolei au fost trimise la adresa de email.");
    }
}

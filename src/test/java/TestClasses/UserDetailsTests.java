package TestClasses;

import Pages.LoginPage;
import Pages.NavigationPage;
import Utils.Constants;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class UserDetailsTests extends BaseTest {

    @Test
    public void editUserTitleTest() {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(Constants.EMAIL, Constants.PASSWORD);
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editUserTitle('M').equals("Dl."));
    }

    @Test
    @Ignore
    public void editUserFirstNameTest() {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(Constants.EMAIL, Constants.PASSWORD);
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editFirstName("John").contains("John"));
    }

    @Test
    @Ignore
    public void editUserLastNameTest() {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(Constants.EMAIL, Constants.PASSWORD);
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editLastName("Smith").contains("Smith"));
    }

    @Test
    @Ignore
    public void editPhoneNumberTest() {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(Constants.EMAIL, Constants.PASSWORD);
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editPhoneNumber("0947583945").equals("0947583945"));
    }

    @Test
    @Ignore
    public void editEmailTest() {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(Constants.EMAIL, Constants.PASSWORD);
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editEmail("automationtesting630@gmail.com").equals("automationtesting630@gmail.com"));
    }

}

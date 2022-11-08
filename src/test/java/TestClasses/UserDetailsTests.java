package TestClasses;

import Pages.LoginPage;
import Pages.NavigationPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class UserDetailsTests extends BaseTest {

    @Test
    @Ignore
    public void editUserTitleTest() {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(email, password);
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editUserTitle('F').equals("Dna."));
    }

    @Test
    public void editUserFirstName() {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(email, password);
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editFirstName("John").contains("John"));
    }

}

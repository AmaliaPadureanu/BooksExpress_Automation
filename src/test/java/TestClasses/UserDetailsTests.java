package TestClasses;

import Pages.LoginPage;
import Pages.NavigationPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserDetailsTests extends BaseTest {

    @Test
    public void editUserInfoTest() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(email, password);
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editUserInfo('F').equals("Dna."));
    }

}

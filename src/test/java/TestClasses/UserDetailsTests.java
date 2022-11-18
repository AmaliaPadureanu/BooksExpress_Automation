package TestClasses;

import Pages.NavigationPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class UserDetailsTests extends BaseTest {

    @BeforeClass
    public void userDetailsTestsSetup() {
        login();
    }

    @Test
    public void editUserTitleTest() {
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editUserTitle('F').equals("Dna."));
    }

    @Test
    public void editUserFirstNameTest() {
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editFirstName("Jenny").contains("Jenny"));
    }

    @Test
    public void editUserLastNameTest() {
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editLastName("Smith").contains("Smith"));
    }

    @Test
    public void editPhoneNumberTest() {
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editPhoneNumber("0947688845").equals("0947688845"));
    }

    @Test
    public void editEmailTest() {
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editEmail("automationtesting631@gmail.com").equals("automationtesting631@gmail.com"));
    }

    private void resetOriginlEmailAddress() {
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        userDetailsPage.editEmail("automationtesting630@gmail.com");
    }

    @AfterClass
    public void userDetailsTestsTearDown() {
        resetOriginlEmailAddress();
        logout();
    }

}

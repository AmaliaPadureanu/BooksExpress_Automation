package TestClasses;

import Pages.NavigationPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
public class UserDetailsTests extends BaseTest {

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
        Assert.assertTrue(userDetailsPage.editFirstName("Sarah").contains("Sarah"));
    }

    @Test
    public void editUserLastNameTest() {
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editLastName("Doe").contains("Doe"));
    }

    @Test
    public void editPhoneNumberTest() {
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editPhoneNumber("0947683945").equals("0947683945"));
    }

    @Test
    public void editEmailTest() {
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertTrue(userDetailsPage.editEmail("automationtesting631@gmail.com").equals("automationtesting631@gmail.com"));
    }

}

package testClasses;

import pages.NavigationPage;
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
        Assert.assertEquals(userDetailsPage.getPageTitle(), "Cont - Detalii personale - Books Express | Books Express");
        Assert.assertTrue(userDetailsPage.editUserTitle('F').equals("Dna."));
    }

    @Test
    public void editUserFirstNameTest() {
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertEquals(userDetailsPage.getPageTitle(), "Cont - Detalii personale - Books Express | Books Express");
        Assert.assertTrue(userDetailsPage.editFirstName("Jenny").contains("Jenny"));
    }

    @Test
    public void editUserLastNameTest() {
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertEquals(userDetailsPage.getPageTitle(), "Cont - Detalii personale - Books Express | Books Express");
        Assert.assertTrue(userDetailsPage.editLastName("Smith").contains("Smith"));
    }

    @Test
    public void editPhoneNumberTest() {
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertEquals(userDetailsPage.getPageTitle(), "Cont - Detalii personale - Books Express | Books Express");
        Assert.assertTrue(userDetailsPage.editPhoneNumber("0957778845").equals("0957778845"));
    }

    @Test
    public void editEmailTest() {
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertEquals(userDetailsPage.getPageTitle(), "Cont - Detalii personale - Books Express | Books Express");
        Assert.assertTrue(userDetailsPage.editEmail("autotesting631@gmail.com").equals("autotesting631@gmail.com"));
    }

    private void resetOriginlEmailAddress() {
        navigationPage = new NavigationPage(driver);
        userDetailsPage = navigationPage.navigateToUserDetails();
        Assert.assertEquals(userDetailsPage.getPageTitle(), "Cont - Detalii personale - Books Express | Books Express");
        userDetailsPage.editEmail("automationtesting630@gmail.com");
    }

    @AfterClass
    public void userDetailsTestsTearDown() {
        resetOriginlEmailAddress();
        logout();
    }

}

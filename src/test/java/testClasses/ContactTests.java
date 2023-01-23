package testClasses;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.NavigationPage;


public class ContactTests extends BaseTest {

    @DataProvider
    public Object[][] contactUnregisteredDataProvider() {
        return new Object[][] {
                {"another subject", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "John Doe", "jdoe@mail.com", "35346"},
                {"another subject", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Andrew Smith", "asmith@mail.com", "457537"},
                {"another subject", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Kyle Edison", "kedison@mail.com", "537"}
        };
    }

    @DataProvider
    public Object[][] contactRegisteredDataProvider() {
        return new Object[][] {
                {"another", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "35346"},
                {"another", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "457537"},
                {"another subject", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "537"}
        };
    }

    @Test(dataProvider = "contactUnregisteredDataProvider")
    public void sendContactFormUnregisteredUserTest(String anotherSubject, String message, String name, String email, String orderNo) throws InterruptedException {
        NavigationPage navigationPage = new NavigationPage(driver);
        contactPage = navigationPage.navigateToContact();
        Assert.assertEquals(contactPage.getPageTitle(), "Contactați-ne | Books Express");

        contactPage.contactAsUnregisteredUser(anotherSubject, message, name, email, orderNo);
        System.out.println(contactPage.getConfirmationMessage());
        Assert.assertTrue(contactPage.getConfirmationMessage().contains("a fost trimis"));
    }

//    @Test (dataProvider = "contactRegisteredDataProvider", enabled = false)
//    public void sendContactFormRegisteredUserTest(String subject, String anotherSubject, String message,String orderNo) {
//        login();
//        NavigationPage navigationPage = new NavigationPage(driver);
//        contactPage = navigationPage.navigateToContact();
//        Assert.assertEquals(contactPage.getPageTitle(), "Contactați-ne | Books Express");
//        contactPage.sendContactFormRegisteredUser(subject, anotherSubject, message, orderNo);
//        Assert.assertTrue(getConfirmationMessage().contains("a fost trimis"));
//        logout();
//    }


}

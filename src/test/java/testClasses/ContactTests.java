package testClasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.NavigationPage;
import testClasses.ObjectModels.ContactModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class ContactTests extends BaseTest {

    @DataProvider
    public Object[][] positiveContactUnregisteredDataProvider() {
        return new Object[][] {
                {"another subject", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "John Doe", "jdoe@mail.com", "35346"},
                {"another subject", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Andrew Smith", "asmith@mail.com", "457537"},
                {"another subject", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Kyle Edison", "kedison@mail.com", "537"}
        };
    }

    @DataProvider
    public Object[][] positiveContactRegisteredDataProvider() {
        return new Object[][] {
                {"another", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "35346"},
                {"another", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "457537"},
                {"another subject", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "537"}
        };
    }

    @DataProvider(name = "negativeContactUnregisteredDataProvider")
    public Iterator<Object[]> jsonDPCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src\\test\\resources\\Data\\invalidContactUnregisteredUserData.json");
        ContactModel[] contactModels = objectMapper.readValue(file, ContactModel[].class);

        for (ContactModel contactModel : contactModels) {
            dp.add(new Object[] {contactModel});
        }
        return dp.iterator();
    }

    private void contactActions(ContactModel contactModel) {
        navigationPage = new NavigationPage(driver);
        contactPage = navigationPage.navigateToContact();
        Assert.assertEquals(contactPage.getPageTitle(), "Contactați-ne | Books Express");
        contactPage.selectSubject(contactModel.getSubject());
        contactPage.fillInContactForm(contactModel.getAnotherSubjectError(), contactModel.getName(), contactModel.getEmail(), contactModel.getOrderNumber());

        Assert.assertTrue(contactPage.checkError(contactModel.getSubjectError(), "subjectError"));
        Assert.assertTrue(contactPage.checkError(contactModel.getAnotherSubjectError(), "anotherSubjectError"));
        Assert.assertTrue(contactPage.checkError(contactModel.getMessageError(), "messageError"));
        Assert.assertTrue(contactPage.checkError(contactModel.getNameError(), "nameError"));
        Assert.assertTrue(contactPage.checkError(contactModel.getEmailError(), "emailError"));
    }

    @Test(dataProvider = "positiveContactUnregisteredDataProvider", groups = {"smoke"})
    public void positiveContactUnregisteredUserTest(String anotherSubject, String message, String name, String email, String orderNo) {
        NavigationPage navigationPage = new NavigationPage(driver);
        contactPage = navigationPage.navigateToContact();
        Assert.assertEquals(contactPage.getPageTitle(), "Contactați-ne | Books Express");
        contactPage.contactAsUnregisteredUser(anotherSubject, message, name, email, orderNo);
        Assert.assertTrue(contactPage.getConfirmationMessage().contains("a fost trimis"));
    }

    @Test (dataProvider = "positiveContactRegisteredDataProvider")
    public void positiveContactRegisteredUserTest(String anotherSubject, String message, String orderNo) {
        NavigationPage navigationPage = new NavigationPage(driver);
        login();
        contactPage = navigationPage.navigateToContact();
        Assert.assertEquals(contactPage.getPageTitle(), "Contactați-ne | Books Express");
        contactPage.contactAsRegisteredUser(anotherSubject, message, orderNo);
        Assert.assertTrue(contactPage.getConfirmationMessage().contains("a fost trimis"));
        loginPage.logout();
    }

    @Test (dataProvider = "negativeContactUnregisteredDataProvider", priority = 1)
    public void negativeContact(ContactModel contactModel) {
        contactActions(contactModel);
    }
}

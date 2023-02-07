package testClasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.NavigationPage;
import testClasses.ObjectModels.ContactModel;
import utils.ConstantUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class ContactTests extends BaseTest {

    @DataProvider(name = "positiveContactUnregisteredDataProvider")
    public Iterator<Object[]> jsonPositiveDPCollection() throws IOException {
        Collection<Object[]> dataProvider = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(ConstantUtils.VALID_CONTACT_UNREGISTERED_USER_JSON_PATH);
        ContactModel[] contactModels = objectMapper.readValue(file, ContactModel[].class);

        for (ContactModel contactModel : contactModels) {
            dataProvider.add(new Object[] {contactModel});
        }

        return dataProvider.iterator();
    }

    @DataProvider(name = "positiveContactRegisteredDataProvider")
    public Iterator<Object[]> jsonPositiveRegisteredDPCollection() throws IOException {
        Collection<Object[]> dataProvider = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(ConstantUtils.VALID_CONTACT_REGISTERED_USER_JSON_PATH);
        ContactModel[] contactModels = objectMapper.readValue(file, ContactModel[].class);

        for (ContactModel contactModel : contactModels) {
            dataProvider.add(new Object[] {contactModel});
        }

        return dataProvider.iterator();
    }

    @DataProvider(name = "negativeContactUnregisteredDataProvider")
    public Iterator<Object[]> jsonDPCollection() throws IOException {
        Collection<Object[]> dataProvider = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(ConstantUtils.INVALID_CONTACT_UNREGISTERED_USER_JSON_PATH);
        ContactModel[] contactModels = objectMapper.readValue(file, ContactModel[].class);

        for (ContactModel contactModel : contactModels) {
            dataProvider.add(new Object[] {contactModel});
        }

        return dataProvider.iterator();
    }

    @Test(dataProvider = "positiveContactUnregisteredDataProvider", groups = {"smoke"})
    public void positiveContactUnregisteredUserTest(ContactModel contactModel) {
        NavigationPage navigationPage = new NavigationPage(driver);
        contactPage = navigationPage.navigateToContact();
        Assert.assertEquals(contactPage.getPageTitle(), "Contactați-ne | Books Express");
        contactPage.contactAsUnregisteredUser(contactModel.getAnotherSubject(), contactModel.getMessage(),
                contactModel.getName(), contactModel.getEmail(), contactModel.getOrderNumber());
        Assert.assertTrue(contactPage.getConfirmationMessage().contains("a fost trimis"));

    }

    @Test (dataProvider = "positiveContactRegisteredDataProvider")
    public void positiveContactRegisteredUserTest(ContactModel contactModel) {
        NavigationPage navigationPage = new NavigationPage(driver);
        login();
        contactPage = navigationPage.navigateToContact();
        Assert.assertEquals(contactPage.getPageTitle(), "Contactați-ne | Books Express");
        contactPage.contactAsRegisteredUser(contactModel.getAnotherSubject(), contactModel.getMessage(), contactModel.getOrderNumber());
        Assert.assertTrue(contactPage.getConfirmationMessage().contains("a fost trimis"));
        logout();
    }

    @Test (dataProvider = "negativeContactUnregisteredDataProvider", priority = 1)
    public void negativeContactUnregisteredUserTest(ContactModel contactModel) {
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
}

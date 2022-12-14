package testClasses;

import org.testng.annotations.DataProvider;
import pages.NavigationPage;
import utils.WaitUtils;
import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactTests extends BaseTest {

    @DataProvider
    public Object[][] contactUnregisteredDataProvider() {
        return new Object[][] {
                {"Unde este comanda mea?", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "John Doe", "jdoe@mail.com", "35346"},
                {"Disponibilitatea unei cărți", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Andrew Smith", "asmith@mail.com", "457537"},
                {"Altul (vă rugăm specificați)", "another subject", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Kyle Edison", "kedison@mail.com", "537"}
        };
    }

    @DataProvider
    public Object[][] contactRegisteredDataProvider() {
        return new Object[][] {
                {"Unde este comanda mea?", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "35346"},
                {"Disponibilitatea unei cărți", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "457537"},
                {"Altul (vă rugăm specificați)", "another subject", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "537"}
        };
    }

    @Test (dataProvider = "contactUnregisteredDataProvider", enabled = false)
    public void sendContactFormUnregisteredUserTest(String subject, String anotherSubject, String message, String name, String email, String orderNo) {
        NavigationPage navigationPage = new NavigationPage(driver);
        contactPage = navigationPage.navigateToContact();
        Assert.assertEquals(contactPage.getPageTitle(), "Contactați-ne | Books Express");
        contactPage.sendContactFormUnregisteredUser(subject, anotherSubject, message, name, email, orderNo);
        Assert.assertTrue(getConfirmationMessage().contains("a fost trimis"));
    }

    @Test (dataProvider = "contactRegisteredDataProvider", enabled = false)
    public void sendContactFormRegisteredUserTest(String subject, String anotherSubject, String message,String orderNo) {
        login();
        NavigationPage navigationPage = new NavigationPage(driver);
        contactPage = navigationPage.navigateToContact();
        Assert.assertEquals(contactPage.getPageTitle(), "Contactați-ne | Books Express");
        contactPage.sendContactFormRegisteredUser(subject, anotherSubject, message, orderNo);
        Assert.assertTrue(getConfirmationMessage().contains("a fost trimis"));
        logout();
    }

    private String getConfirmationMessage() {
       return WaitUtils.waitForVisibilityOf(driver, By.xpath("//div[@id='success']//div[1]"), 5).getText();
    }

}

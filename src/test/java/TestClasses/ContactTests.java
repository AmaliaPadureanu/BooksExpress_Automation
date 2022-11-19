package TestClasses;

import Pages.NavigationPage;
import Utils.WaitsUtils;
import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactTests extends BaseTest {

    @Test
    public void sendContactFormUnregisteredUserTest() {
        NavigationPage navigationPage = new NavigationPage(driver);
        contactPage = navigationPage.navigateToContact();
        contactPage.sendContactFormUnregisteredUser("Unde este comanda mea?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "John Doe", "jdoe@gmail.com", "34667");
        Assert.assertTrue(getConfirmationMessage().contains("a fost trimis"));
    }

    @Test
    public void sendContactFormRegisteredUserTest() {
        login();
        NavigationPage navigationPage = new NavigationPage(driver);
        contactPage = navigationPage.navigateToContact();
        contactPage.sendContactFormRegisteredUser("Unde este comanda mea?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "34667");
        Assert.assertTrue(getConfirmationMessage().contains("a fost trimis"));
        logout();
    }

    private String getConfirmationMessage() {
       return WaitsUtils.waitForVisibilityOf(driver, By.xpath("//div[@id='success']//div[1]"), 5).getText();
    }

}

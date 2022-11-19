package TestClasses;

import Pages.NavigationPage;
import Utils.WaitUtils;
import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactTests extends BaseTest {

    @Test
    public void sendContactFormUnregisteredUserTest() {
        NavigationPage navigationPage = new NavigationPage(driver);
        contactPage = navigationPage.navigateToContact();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.books-express.ro/contact");
        contactPage.sendContactFormUnregisteredUser("Unde este comanda mea?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "John Doe", "jdoe@gmail.com", "34667");
        Assert.assertTrue(getConfirmationMessage().contains("a fost trimis"));
    }

    @Test
    public void sendContactFormRegisteredUserTest() {
        login();
        NavigationPage navigationPage = new NavigationPage(driver);
        contactPage = navigationPage.navigateToContact();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.books-express.ro/contact");
        contactPage.sendContactFormRegisteredUser("Unde este comanda mea?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "34667");
        Assert.assertTrue(getConfirmationMessage().contains("a fost trimis"));
        logout();
    }

    private String getConfirmationMessage() {
       return WaitUtils.waitForVisibilityOf(driver, By.xpath("//div[@id='success']//div[1]"), 5).getText();
    }

}

package TestClasses;

import Pages.NavigationPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactTests extends BaseTest {

    private String MESSAGE_SENT_TEXT = "//div[@id='success']//div[1]";

    @Test (enabled = false)
    public void sendContactFormUnregisteredUserTest() {
        NavigationPage navigationPage = new NavigationPage(driver);
        contactPage = navigationPage.navigateToContact();
        contactPage.sendContactFormUnregisteredUser("Unde este comanda mea?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "John Doe", "jdoe@gmail.com", "34667");
        Assert.assertTrue(getConfirmationMessage().contains("a fost trimis"));
    }

    @Test (enabled = true)
    public void sendContactFormRegisteredUserTest() {
        NavigationPage navigationPage = new NavigationPage(driver);
        contactPage = navigationPage.navigateToContact();
        contactPage.sendContactFormRegisteredUser("Unde este comanda mea?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "34667");
        Assert.assertTrue(getConfirmationMessage().contains("a fost trimis"));
    }

    private String getConfirmationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String message = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(MESSAGE_SENT_TEXT)))).getText();
        return message;
    }
}

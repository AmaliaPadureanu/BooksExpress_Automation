package TestClasses;

import Pages.NavigationPage;
import base.BaseTest;
import org.testng.annotations.Test;

public class ContactTests extends BaseTest {

    @Test
    public void sendContactFormUnregisteredUserTest() {
        NavigationPage navigationPage = new NavigationPage(driver);
        contactPage = navigationPage.contact();
        contactPage.sendContactFormUnregisteredUser("Unde este comanda mea?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "John Doe", "jdoe@gmail.com", "34667");
    }
}
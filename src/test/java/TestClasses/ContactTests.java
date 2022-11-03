package TestClasses;

import Pages.NavigationPage;
import base.BaseTest;
import org.testng.annotations.Test;

public class ContactTests extends BaseTest {

    @Test
    public void sendContactFormUnregisteredUser() {
        NavigationPage navigationPage = new NavigationPage(driver);
        contactPage = navigationPage.contact();
        contactPage.sendContactFormUnregisteredUser("Unde este comanda mea?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "John", "Doe", "34667");
    }
}

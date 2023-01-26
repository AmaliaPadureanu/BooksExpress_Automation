package testClasses;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.NavigationPage;
import pages.NewsletterPage;

public class NewsletterTests extends BaseTest {

    @Test
    public void subscribeToNewsletter() {
        navigationPage = new NavigationPage(driver);
        NewsletterPage newsletterPage = navigationPage.navigateToNewsletter();
        Assert.assertTrue(newsletterPage.getPageTitle().contains("Înscriere newsletter"));
        newsletterPage.subscribeToNewsletter("funnyb@yaho.com", "Funny Bunny", true);
        Assert.assertEquals(newsletterPage.getAfterSubscribeMessage(), "Adresa de email și preferințele tale au fost salvate.");
    }
}

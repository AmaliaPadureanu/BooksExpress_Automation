package testClasses;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.NavigationPage;
import pages.NewsletterPage;

public class NewsletterTests extends BaseTest {

    @Test
    public void positiveSubscribeToNewsletter() {
        navigationPage = new NavigationPage(driver);
        NewsletterPage newsletterPage = navigationPage.navigateToNewsletter();
        Assert.assertTrue(newsletterPage.getPageTitle().contains("Înscriere newsletter"));
        newsletterPage.subscribeToNewsletter("funnyb@yaho.com", "Funny Bunny", true);
        Assert.assertEquals(newsletterPage.getAfterSubscribeMessage(), "Adresa de email și preferințele tale au fost salvate.");
    }

    @Test
    public void negativeSubscribeToNewsletter() {
        navigationPage = new NavigationPage(driver);
        NewsletterPage newsletterPage = navigationPage.navigateToNewsletter();
        Assert.assertTrue(newsletterPage.getPageTitle().contains("Înscriere newsletter"));
        newsletterPage.subscribeToNewsletter("", "Funny Bunny", true);
        Assert.assertEquals(newsletterPage.getEmailError(), "Te rugăm să-ți introduci adresa de email");
    }
}

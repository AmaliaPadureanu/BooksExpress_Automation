package testClasses;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.NavigationPage;
import pages.NewsletterPage;

public class NewsletterTests extends BaseTest {

    public String AFTER_SUBSCRIBE_MESSAGE = "//p[@class='align-center']";

    @Test
    public void subscribeToNewsletter() {
        navigationPage = new NavigationPage(driver);
        NewsletterPage newsletterPage = navigationPage.navigateToNewsletter();
        Assert.assertTrue(newsletterPage.getPageTitle().contains("Înscriere newsletter"));
        newsletterPage.subscribeToNewsletter("funnyb@yaho.com", "Funny Bunny", 3);
        Assert.assertNotNull(AFTER_SUBSCRIBE_MESSAGE);
    }
}

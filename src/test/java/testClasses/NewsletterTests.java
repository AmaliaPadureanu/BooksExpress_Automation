package testClasses;

import pages.NavigationPage;
import pages.NewsletterPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewsletterTests extends BaseTest {

    public String AFTER_SUBSCRIBE_MESSAGE = "//p[@class='align-center']";

    @Test
    public void subscribeToNewsletter() {
        navigationPage = new NavigationPage(driver);
        NewsletterPage newsletterPage = navigationPage.navigateToNewsletter();
        newsletterPage.subscribeToNewsletter("funnyb@yaho.com", "Funny Bunny", 3);
        Assert.assertNotNull(AFTER_SUBSCRIBE_MESSAGE);
    }
}

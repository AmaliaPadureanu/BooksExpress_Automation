package TestClasses;

import Pages.NavigationPage;
import Pages.NewsletterPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class NavigationTests extends BaseTest {

    public String REDUCERI_URL = "https://www.books-express.ro/reduceri";
    public String TOP_VANZARI_URL = "https://www.books-express.ro/top/carti";
    public String NOUTATI_URL = "https://www.books-express.ro/carti?n=1";
    public String CADOURI_URL = "https://www.books-express.ro/carti/lifestyle-cadouri/wzg";
    public String BLOG_URL = "https://www.books-express.ro/blog/";
    public String NEWSLETTER_URL = "https://www.books-express.ro/newsletter";
    public String CONTACT_URL = "https://www.books-express.ro/contact";
    public String AFTER_SUBSCRIBE_MESSAGE = "//p[@class='align-center']";
    public String CATEGORY_TITLE = "umaniste";

    @Test
    public void searchProductsCategoryTest() throws InterruptedException {
        navigationPage = new NavigationPage(driver);
        //System.out.println(navigationPage.selectProductsCategory());
        Assert.assertTrue(navigationPage.selectProductsCategory().contains("economie"));
    }

    @Test
    @Ignore
    public void openSalesPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.reduceri();
        Assert.assertTrue(driver.getCurrentUrl().equals(REDUCERI_URL));
    }

    @Test
    @Ignore
    public void openTopSalesPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.topVanzari();
        Assert.assertTrue(driver.getCurrentUrl().equals(TOP_VANZARI_URL));
    }

    @Test
    @Ignore
    public void openNoveltiesPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.noutati();
        Assert.assertTrue(driver.getCurrentUrl().equals(NOUTATI_URL));
    }

    @Test
    @Ignore
    public void openGiftsTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.cadouri();
        Assert.assertTrue(driver.getCurrentUrl().equals(CADOURI_URL));
    }
    @Test
    @Ignore
    public void openBlogPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.blog();
        Assert.assertTrue(driver.getCurrentUrl().equals(BLOG_URL));
    }

    @Test
    @Ignore
    public void openNewsletterPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.newsletter();
        Assert.assertTrue(driver.getCurrentUrl().equals(NEWSLETTER_URL));
    }

    @Test
    @Ignore
    public void openContactPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.contact();
        Assert.assertTrue(driver.getCurrentUrl().equals(CONTACT_URL));
    }

    @Test
    @Ignore
    public void subscribeToNewsletter() {
        navigationPage = new NavigationPage(driver);
        NewsletterPage newsletterPage = navigationPage.newsletter();
        newsletterPage.subscribeToNewsletter("funnybunn@yaho.com", "Funny Bunny", "ger");
        Assert.assertNotNull(AFTER_SUBSCRIBE_MESSAGE);
    }

}

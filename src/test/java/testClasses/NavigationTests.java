package testClasses;

import pages.NavigationPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTests extends BaseTest {

    public String REDUCERI_URL = "https://www.books-express.ro/reduceri";
    public String TOP_VANZARI_URL = "https://www.books-express.ro/top/carti";
    public String NOUTATI_URL = "https://www.books-express.ro/carti?n=1";
    public String CADOURI_URL = "https://www.books-express.ro/carti/lifestyle-cadouri/wzg";
    public String BLOG_URL = "https://www.books-express.ro/blog/";
    public String NEWSLETTER_URL = "https://www.books-express.ro/newsletter";
    public String CONTACT_URL = "https://www.books-express.ro/contact";

    @Test
    public void searchProductsCategoryTest() {
        navigationPage = new NavigationPage(driver);
        Assert.assertTrue(navigationPage.selectProductsCategory().contains("economie"));
    }

    @Test
    public void openSalesPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToSales();
        Assert.assertTrue(driver.getCurrentUrl().equals(REDUCERI_URL));
    }

    @Test
    public void openTopSalesPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToTopSales();
        Assert.assertTrue(driver.getCurrentUrl().equals(TOP_VANZARI_URL));
    }

    @Test
    public void openNoveltiesPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToNovelties();
        Assert.assertTrue(driver.getCurrentUrl().equals(NOUTATI_URL));
    }

    @Test
    public void openGiftsTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToGifts();
        Assert.assertTrue(driver.getCurrentUrl().equals(CADOURI_URL));
    }
    @Test
    public void openBlogPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.blog();
        Assert.assertTrue(driver.getCurrentUrl().equals(BLOG_URL));
    }

    @Test
    public void openNewsletterPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToNewsletter();
        Assert.assertTrue(driver.getCurrentUrl().equals(NEWSLETTER_URL));
    }

    @Test
    public void openContactPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToContact();
        Assert.assertTrue(driver.getCurrentUrl().equals(CONTACT_URL));
    }
}

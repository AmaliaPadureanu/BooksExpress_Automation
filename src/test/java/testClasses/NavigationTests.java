package testClasses;

import pages.NavigationPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConstantUtils;
import utils.SeleniumUtils;

public class NavigationTests extends BaseTest {

    @Test
    public void navigateToSalesTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToSales();
        Assert.assertTrue(SeleniumUtils.getCurrentURL(driver).equals(ConstantUtils.SALES_URL));
    }

    @Test
    public void navigateToTopSalesPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToTopSales();
        Assert.assertTrue(SeleniumUtils.getCurrentURL(driver).equals(ConstantUtils.TOP_SALES_URL));
    }

    @Test
    public void navigateToNewProductsPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToNewProducts();
        Assert.assertTrue(SeleniumUtils.getCurrentURL(driver).equals(ConstantUtils.NEW_PRODUCTS_URL));
    }

    @Test
    public void navigateToGiftsTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToGifts();
        Assert.assertTrue(SeleniumUtils.getCurrentURL(driver).equals(ConstantUtils.GIFTS_URL));
    }
    @Test
    public void navigateToBlogPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToBlog();
        Assert.assertTrue(SeleniumUtils.getCurrentURL(driver).equals(ConstantUtils.BLOG_URL));
    }

    @Test
    public void navigateToNewsletterPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToNewsletter();
        Assert.assertTrue(SeleniumUtils.getCurrentURL(driver).equals(ConstantUtils.NEWSLETTER_URL));
    }

    @Test
    public void navigateToContactPageTest() {
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToContact();
        Assert.assertTrue(SeleniumUtils.getCurrentURL(driver).equals(ConstantUtils.CONTACT_URL));
    }
}

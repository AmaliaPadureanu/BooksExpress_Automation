package TestClasses;

import Pages.LoginPage;
import Pages.ResultsPage;
import Pages.SearchPage;
import Utils.Constants;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WishlistTests extends BaseTest {

    @Test
    public void addToWishlist() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(Constants.EMAIL, Constants.PASSWORD);
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("the secret history");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        String title = itemDetailsPage.getItemTitle().substring(3, 10);
        wishlistPage = itemDetailsPage.addToWishlist();
        Thread.sleep(6000);
        wishlistPage.navigateToWishlist();
        Thread.sleep(3000);
        Assert.assertTrue(wishlistPage.getItemTitle().contains(title));
    }

    @Test
    public void removeFromWishlist() throws InterruptedException {
        wishlistPage.removeItem();
        Thread.sleep(3000);
        Assert.assertTrue(wishlistPage.isEmpty());
    }
}

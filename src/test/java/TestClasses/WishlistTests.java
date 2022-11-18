package TestClasses;

import Pages.SearchResultsPage;
import Pages.SearchPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WishlistTests extends BaseTest {

    @BeforeClass
    public void wishlistTestsSetup() {
        login();
    }

    @Test
    public void addToWishlist() throws InterruptedException {
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search("the secret history");
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        String title = itemDetailsPage.getItemTitle();
        wishlistPage = itemDetailsPage.addToWishlist();
        Thread.sleep(6000);
        wishlistPage.navigateToWishlist();
        Thread.sleep(3000);
        Assert.assertTrue(wishlistPage.getItemTitle().contains(title));
    }

    @Test
    public void removeFromWishlist() throws InterruptedException {
        wishlistPage.removeItem();
        Thread.sleep(6000);
        Assert.assertTrue(wishlistPage.isEmpty());
    }

    @AfterClass
    public void wishlistTestsTearDown() {
        logout();
    }
}

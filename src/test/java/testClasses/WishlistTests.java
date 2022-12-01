package testClasses;

import pages.NavigationPage;
import pages.SearchPage;
import pages.SearchResultsPage;
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
        String searchText = "It Starts with Us";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        String title = itemDetailsPage.getItemTitle();
        itemDetailsPage.addToWishlist();
        Thread.sleep(6000);
        navigationPage = new NavigationPage(driver);
        wishlistPage = navigationPage.navigateToWishlist();
        Assert.assertEquals(wishlistPage.getPageTitle(), "Liste Express - Books Express | Books Express");
        Assert.assertTrue(wishlistPage.getItemsTitle().contains(searchText));
    }

    @Test
    public void removeFromWishlist() {
        wishlistPage.removeItem();
        driver.navigate().refresh();
        Assert.assertTrue(wishlistPage.isEmpty());
    }

    @AfterClass
    public void wishlistTestsTearDown() {
        logout();
    }
}

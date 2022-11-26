package TestClasses;

import Pages.NavigationPage;
import Pages.SearchPage;
import Pages.SearchResultsPage;
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
        SearchResultsPage searchResultsPage = searchPage.search("It Starts with Us");
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        String title = itemDetailsPage.getItemTitle();
        itemDetailsPage.addToWishlist();
        Thread.sleep(6000);
        navigationPage = new NavigationPage(driver);
        wishlistPage = navigationPage.navigateToWishlist();
        Assert.assertTrue(wishlistPage.getItemsTitle().contains(title));
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

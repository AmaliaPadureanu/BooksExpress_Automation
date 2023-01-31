package testClasses;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.NavigationPage;
import pages.SearchPage;
import utils.SeleniumUtils;
import utils.WaitUtils;

public class WishlistTests extends BaseTest {

    @BeforeClass (alwaysRun = true)
    public void wishlistTestsSetup() {
        login();
    }

    @Test
    public void addToWishlist() {
        searchPage = new SearchPage(driver);
        searchResultsPage = searchPage.searchRandomProduct();
        itemDetailsPage = searchResultsPage.getRandomProduct();
        String productName = itemDetailsPage.getItemTitle().substring(0, 10);
        itemDetailsPage.addToWishlist();
        navigationPage = new NavigationPage(driver);
        wishlistPage = navigationPage.navigateToWishlist();
        WaitUtils.waitForUrlToBe(driver, "https://www.books-express.ro/user/wishlist", 5);
        Assert.assertEquals(wishlistPage.getPageTitle(), "Liste Express - Books Express | Books Express");
        Assert.assertTrue(wishlistPage.getItemsTitle().contains(productName));
    }

    @Test
    public void removeFromWishlist() {
        int itemsBeforeRemoval = wishlistPage.getNumberOfItemsInWishlist();
        wishlistPage.removeItem(0);
        SeleniumUtils.refreshPage(driver);
        Assert.assertTrue(wishlistPage.getNumberOfItemsInWishlist() == itemsBeforeRemoval-1);
    }

    @AfterClass
    public void wishlistTestsTearDown() {
        logout();
    }
}

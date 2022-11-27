package TestClasses;

import Pages.NavigationPage;
import Pages.SearchPage;
import Pages.SearchResultsPage;
import Pages.ShoppingCartPage;
import Utils.WaitUtils;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCartTests extends BaseTest {

    @Test (enabled = false)
    public void addToCartTest() {
        String searchText = "Fire and Blood";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.addToCart());
    }

    @Test (enabled = false)
    public void changeQuantityTest() {
        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        Assert.assertEquals(shoppingCartPage.changeQuantity(7), 7);
    }

    @Test (enabled = false)
    public void removeFromCartTest() throws InterruptedException {
        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        int itemsNoBeforeRemoval = shoppingCartPage.getNoOfCartItems();
        Assert.assertTrue(shoppingCartPage.removeFromCart() == itemsNoBeforeRemoval - 1);
    }

    @Test (priority = 1, enabled = false)
    public void checkCartTotal() {
        addMultipleItemsToCart("Fire and Blood", "Little Women", "No Longer Human", "Currency Wars", "A Darker Shade of Magic");
        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        WaitUtils.waitForUrlToBe(driver, "https://www.books-express.ro/cart", 10);
        Assert.assertEquals(shoppingCartPage.computeTotalCartPrice(), shoppingCartPage.getTotalPriceFormatted());
    }

    private void addMultipleItemsToCart(String... items) {
        List<String> searchText = new ArrayList<>();
        Collections.addAll(searchText, items);
        searchPage = new SearchPage(driver);

        for (String searchItem : searchText) {
            SearchResultsPage searchResultsPage = searchPage.search(searchItem);
            itemDetailsPage = searchResultsPage.getItemDetailsPage(searchItem);
            itemDetailsPage.addToCart();
        }
    }

    @Test
    public void checkDeliveryPrice() {
        String searchText = "Fire and Blood";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.addToCart());
        NavigationPage navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        Assert.assertEquals(shoppingCartPage.changeQuantity(5), 5);
        if (shoppingCartPage.getTotalPriceFormatted() > 300.00) {
            Assert.assertEquals(shoppingCartPage.getDeliveryPriceFormatted(), "GRATUIT");
        } else {
            Assert.assertEquals(shoppingCartPage.getDeliveryPriceFormatted(), 9.99);
        }
    }

}

package testClasses;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.NavigationPage;
import pages.SearchPage;
import pages.SearchResultsPage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCartTests extends BaseTest {

    @Test
    public void addToCartTest() {
        String searchText = "Harry Potter and the Half-Blood Prince";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        //Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        //Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        Assert.assertTrue(itemDetailsPage.addToCart());
    }

    @Test
    public void changeQuantityTest() {
        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        //Assert.assertEquals(shoppingCartPage.getPageTitle(), "Coș de cumpărături | Books Express");
        Assert.assertEquals(shoppingCartPage.changeQuantity(7), 7);
    }

    @Test
    public void removeFromCartTest() {
        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        //Assert.assertEquals(shoppingCartPage.getPageTitle(), "Coș de cumpărături | Books Express");
        int itemsNoBeforeRemoval = shoppingCartPage.getNoOfCartItems();
        Assert.assertTrue(shoppingCartPage.removeFromCart() == itemsNoBeforeRemoval - 1);
    }

    @Test (priority = 1)
    public void checkCartTotal() {
        addMultipleItemsToCart("Harry Potter and the Half-Blood Prince", "Little Women", "No Longer Human", "Currency Wars", "A Darker Shade of Magic");
        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        //Assert.assertEquals(shoppingCartPage.getPageTitle(), "Coș de cumpărături | Books Express");
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

    @Test (dependsOnMethods = {"addToCartTest"})
    public void checkFreeDelivery() {
        String searchText = "Harry Potter and the Half-Blood Prince";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        //Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        //Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        Assert.assertTrue(itemDetailsPage.addToCart());
        NavigationPage navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        //Assert.assertEquals(shoppingCartPage.getPageTitle(), "Coș de cumpărături | Books Express");
        Assert.assertEquals(shoppingCartPage.changeQuantity(5), 5);
        driver.navigate().refresh();
        Assert.assertEquals(shoppingCartPage.getDeliveryPriceFormatted(), "GRATUIT");
    }

    @Test
    public void checkDeliveryPrice() {
        String searchText = "Harry Potter and the Half-Blood Prince";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        //Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        //Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        Assert.assertTrue(itemDetailsPage.addToCart());
        NavigationPage navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        //Assert.assertEquals(shoppingCartPage.getPageTitle(), "Coș de cumpărături | Books Express");
        Assert.assertEquals(shoppingCartPage.changeQuantity(1), 1);
        driver.navigate().refresh();
        Assert.assertEquals(shoppingCartPage.getDeliveryPriceFormatted(), 9.99);
    }

}

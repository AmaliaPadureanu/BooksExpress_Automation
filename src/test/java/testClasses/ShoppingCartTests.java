package testClasses;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.NavigationPage;
import pages.SearchPage;
import pages.SearchResultsPage;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCartTests extends BaseTest {

    @Test
    public void addToCartTest() {
        searchPage = new SearchPage(driver);
        searchResultsPage = searchPage.searchRandomProduct();
        itemDetailsPage = searchResultsPage.getRandomProduct();
        shoppingCartPage = itemDetailsPage.addToCart();
        WaitUtils.waitForUrlToContain(driver, "https://www.books-express.ro/cart/added/", 10);
        Assert.assertTrue(shoppingCartPage.getCartSuccessMessage().contains(" a fost adăugat în coș."));
    }

    @Test
    public void changeQuantityTest() {
        searchPage = new SearchPage(driver);
        searchResultsPage = searchPage.searchRandomProduct();
        itemDetailsPage = searchResultsPage.getRandomProduct();
        shoppingCartPage = itemDetailsPage.addToCart();
        WaitUtils.waitForUrlToContain(driver, "https://www.books-express.ro/cart/added/", 10);
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToCart();
        WaitUtils.waitForUrlToBe(driver, "https://www.books-express.ro/cart", 10);
        Assert.assertEquals(shoppingCartPage.getPageTitle(), "Coș de cumpărături | Books Express");
        int initialSubtotal = shoppingCartPage.getProductSubtotal(shoppingCartPage.getProductsInCart().get(0));
        shoppingCartPage.changeQuantity("3");
        WaitUtils.wait(driver, 5);
        Assert.assertEquals((shoppingCartPage.getProductSubtotal(shoppingCartPage.getProductsInCart().get(0))), initialSubtotal * 3);

    }

    @Test
    public void removeFromCartTest() {
        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        Assert.assertEquals(shoppingCartPage.getPageTitle(), "Coș de cumpărături | Books Express");
        int itemsNoBeforeRemoval = shoppingCartPage.getNoOfCartItems();
        Assert.assertTrue(shoppingCartPage.removeFromCart() == itemsNoBeforeRemoval - 1);
    }

    @Test (priority = 1)
    public void checkCartTotal() {
        addMultipleItemsToCart("Harry Potter and the Half-Blood Prince", "Little Women", "No Longer Human", "Currency Wars", "A Darker Shade of Magic");
        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        Assert.assertEquals(shoppingCartPage.getPageTitle(), "Coș de cumpărături | Books Express");
        Assert.assertEquals(shoppingCartPage.computeTotalCartPrice(), shoppingCartPage.getTotalPriceFormatted());
    }

    private void addMultipleItemsToCart(String... items) {
        List<String> searchText = new ArrayList<>();
        Collections.addAll(searchText, items);

        for (String searchItem : searchText) {

            searchPage = new SearchPage(driver);
            SearchResultsPage searchResultsPage = searchPage.search(searchItem);
            Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchItem));
            itemDetailsPage = searchResultsPage.getFirstItemPage();
            Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchItem));
            //Assert.assertTrue(itemDetailsPage.addToCart());
        }
    }

    @Test (dependsOnMethods = {"addToCartTest"})
    public void checkFreeDelivery() {
        String searchText = "Harry Potter and the Half-Blood Prince";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getFirstItemPage();
        WaitUtils.wait(driver, 5);
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        //Assert.assertTrue(itemDetailsPage.addToCart());
        NavigationPage navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        Assert.assertEquals(shoppingCartPage.getPageTitle(), "Coș de cumpărături | Books Express");
        //Assert.assertEquals(shoppingCartPage.changeQuantity(5), 5);
        driver.navigate().refresh();
        Assert.assertEquals(shoppingCartPage.getDeliveryPriceFormatted(), "GRATUIT");
    }

    @Test
    public void checkDeliveryPrice() {
        String searchText = "Harry Potter and the Half-Blood Prince";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getFirstItemPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        //Assert.assertTrue(itemDetailsPage.addToCart());
        NavigationPage navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        WaitUtils.wait(driver, 5);
        Assert.assertEquals(shoppingCartPage.getPageTitle(), "Coș de cumpărături | Books Express");
        //Assert.assertEquals(shoppingCartPage.changeQuantity(1), 1);
        driver.navigate().refresh();
        Assert.assertEquals(shoppingCartPage.getDeliveryPriceFormatted(), 9.99);
    }

}

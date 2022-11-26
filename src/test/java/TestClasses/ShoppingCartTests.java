package TestClasses;

import Pages.SearchResultsPage;
import Pages.SearchPage;
import Pages.ShoppingCartPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingCartTests extends BaseTest {

    @Test
    public void addToCartTest() {
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search("George Martin");
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.addToCart());
    }

    @Test
    public void changeQuantityTest() {
        shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.navigateToCart();
        Assert.assertEquals(shoppingCartPage.changeQuantity(7), 7);
    }

    @Test
    public void removeFromCartTest() throws InterruptedException {
        shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.navigateToCart();
        int itemsNoBeforeRemoval = shoppingCartPage.getNoOfCartItems();
        Assert.assertTrue(shoppingCartPage.removeFromCart() == (itemsNoBeforeRemoval - 1));
    }

}

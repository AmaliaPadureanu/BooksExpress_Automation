package TestClasses;

import Pages.NavigationPage;
import Pages.SearchPage;
import Pages.SearchResultsPage;
import Utils.WaitUtils;
import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCartTests extends BaseTest {

    @Test
    public void addToCartTest() {
        String searchText = "Fire and Blood";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.addToCart());
    }

    @Test
    public void changeQuantityTest() {
        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        Assert.assertEquals(driver.findElement(By.id("title")).getText(), "Coș de cumpărături");
        Assert.assertEquals(shoppingCartPage.changeQuantity(7), 7);
    }

    @Test
    public void removeFromCartTest() throws InterruptedException {
        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        Assert.assertEquals(driver.findElement(By.id("title")).getText(), "Coș de cumpărături");
        int itemsNoBeforeRemoval = shoppingCartPage.getNoOfCartItems();
        Assert.assertTrue(shoppingCartPage.removeFromCart() == itemsNoBeforeRemoval - 1);
    }

    @Test (priority = 1)
    public void checkCartTotal() {
        addMultipleItemsToCart("Fire and Blood", "Little Women", "No Longer Human", "Currency Wars", "A Darker Shade of Magic");
        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        Assert.assertEquals(driver.findElement(By.id("title")).getText(), "Coș de cumpărături");
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
    public void checkFreeDelivery() {
        String searchText = "Fire and Blood";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.addToCart());
        NavigationPage navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        Assert.assertEquals(driver.findElement(By.id("title")).getText(), "Coș de cumpărături");
        Assert.assertEquals(shoppingCartPage.changeQuantity(5), 5);
        driver.navigate().refresh();
        Assert.assertEquals(shoppingCartPage.getDeliveryPriceFormatted(), "GRATUIT");
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
        Assert.assertEquals(driver.findElement(By.id("title")).getText(), "Coș de cumpărături");
        Assert.assertEquals(shoppingCartPage.changeQuantity(1), 1);
        driver.navigate().refresh();
        Assert.assertEquals(shoppingCartPage.getDeliveryPriceFormatted(), 9.99);
    }

}

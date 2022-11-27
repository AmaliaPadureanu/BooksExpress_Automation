package TestClasses;

import Pages.NavigationPage;
import Pages.SearchPage;
import Pages.SearchResultsPage;
import Utils.WaitUtils;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
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
        Assert.assertEquals(shoppingCartPage.changeQuantity(7), 7);
    }

    @Test
    public void removeFromCartTest() throws InterruptedException {
        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        int itemsNoBeforeRemoval = shoppingCartPage.getNoOfCartItems();
        Assert.assertTrue(shoppingCartPage.removeFromCart() == itemsNoBeforeRemoval - 1);
    }

    @Test (priority = 1)
    public void checkCartTotal() {
        List<String> searchText = new ArrayList<>();
        searchText.add("Fire and Blood");
        searchText.add("Little Women");
        searchText.add("No Longer Human");
        searchText.add("Currency Wars");

        searchPage = new SearchPage(driver);

        for (String searchItem : searchText) {
            SearchResultsPage searchResultsPage = searchPage.search(searchItem);
            itemDetailsPage = searchResultsPage.getItemDetailsPage(searchItem);
            Assert.assertTrue(itemDetailsPage.addToCart());
        }

        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();

        WaitUtils.waitForUrlToBe(driver, "https://www.books-express.ro/cart", 10);

        List<WebElement> items = driver.findElements(By.xpath("//div[@class='color-theme-5 line']"));

        String totalPriceOnSite = driver.findElement(By.cssSelector("div[class='12u$(large) 4u'] div[class='products-total line strong']"))
                .getText().replace(" lei", "");
        Double totalPriceOnSiteFormatted = shoppingCartPage.formatPrice(Integer.valueOf(totalPriceOnSite));

        Double totalPrice = 0.0;

        for (WebElement item : items) {
            totalPrice += shoppingCartPage.formatPrice(Integer.valueOf(item.getText().replace(" lei", "")));

        }
        Assert.assertEquals(totalPrice, totalPriceOnSiteFormatted);
    }



}

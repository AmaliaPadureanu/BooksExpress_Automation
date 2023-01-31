package testClasses;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.NavigationPage;
import pages.SearchPage;
import utils.WaitUtils;

public class ShoppingCartTests extends BaseTest {

    @Test (groups = {"smoke"})
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
        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        WaitUtils.waitForUrlToBe(driver, "https://www.books-express.ro/cart", 10);
        Assert.assertEquals(shoppingCartPage.getPageTitle(), "Coș de cumpărături | Books Express");
        int initialSubtotal = shoppingCartPage.getProductSubtotal(shoppingCartPage.getProductsInCart().get(0));
        shoppingCartPage.changeQuantityForProduct(shoppingCartPage.getProductsInCart().get(0), "3");
        int currentQuantity = shoppingCartPage.getValueInQuantityBox(shoppingCartPage.getProductsInCart().get(0));
        Assert.assertEquals((shoppingCartPage.getProductSubtotal(shoppingCartPage.getProductsInCart().get(0))), initialSubtotal * currentQuantity);
        Assert.assertEquals(shoppingCartPage.checkDeliveryPrice(), shoppingCartPage.getDeliveryPrice());
    }

    @Test
    public void removeFromCartTest() {
        navigationPage = new NavigationPage(driver);
        shoppingCartPage = navigationPage.navigateToCart();
        Assert.assertEquals(shoppingCartPage.getPageTitle(), "Coș de cumpărături | Books Express");
        int itemsNoBeforeRemoval = shoppingCartPage.getNumberOfProductsInCart();
        shoppingCartPage.removeFromCart(shoppingCartPage.getProductsInCart().get(0));
        Assert.assertTrue(shoppingCartPage.getNumberOfProductsInCart() == itemsNoBeforeRemoval - 1);
    }

}

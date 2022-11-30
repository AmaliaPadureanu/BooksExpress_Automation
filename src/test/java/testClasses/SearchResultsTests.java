package testClasses;

import pages.SearchResultsPage;
import pages.SearchPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchResultsTests extends BaseTest {

    @Test
    public void filterPriceInAscendingOrderTest() {
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search("George Martin");
        searchResultsPage.filterAscendingOrder();
        Assert.assertTrue(searchResultsPage.checkAscendingOrder());
    }

    @Test
    public void filterPriceInDescendingOrderTest() {
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search("George Martin");
        searchResultsPage.filterDescendingOrder();
        Assert.assertTrue(searchResultsPage.checkDescendingOrder());
    }

}

package testClasses;

import pages.SearchResultsPage;
import pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchResultsTests extends BaseTest {

    String searchText;

    @Test
    public void filterPriceInAscendingOrderTest() {
        searchText = "George Martin";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        searchResultsPage.filterAscendingOrder();
        Assert.assertTrue(searchResultsPage.checkAscendingOrder());
    }

    @Test
    public void filterPriceInDescendingOrderTest() {
        searchText = "George Martin";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search("George Martin");
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        searchResultsPage.filterDescendingOrder();
        Assert.assertTrue(searchResultsPage.checkDescendingOrder());
    }

    @Test
    public void filterByLanguageTest() {
        String language = "ita";
        searchText = "computers";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        searchResultsPage.filterByLanguage(language);
        itemDetailsPage = searchResultsPage.getFirstItemInList();
        Assert.assertTrue(itemDetailsPage.getProductLanguage().toLowerCase().contains(language));
    }

}

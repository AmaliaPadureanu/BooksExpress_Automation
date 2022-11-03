package TestClasses;

import Pages.ResultsPage;
import Pages.SearchPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResultsTests extends BaseTest {

    @Test
    public void filterPriceInAscendingOrder() {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("George Martin");
        resultsPage.filterAscendingOrder();
    }

    @Test
    public void filterPriceInDescendingOrder() {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("George Martin");
        resultsPage.filterDescendingOrder();
    }

    @Test
    public void isAscending() {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("George Martin");
        resultsPage.filterAscendingOrder();
        Assert.assertTrue(resultsPage.checkAscendingOrder());
    }

    @Test
    public void isDescending() {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("George Martin");
        resultsPage.filterDescendingOrder();
        Assert.assertTrue(resultsPage.checkDescendingOrder());
    }
}

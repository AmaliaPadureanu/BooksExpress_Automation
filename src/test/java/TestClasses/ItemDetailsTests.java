package TestClasses;

import Pages.ResultsPage;
import Pages.SearchPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ItemDetailsTests extends BaseTest {

    @Test
    public void getItemTitle() {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("George Martin");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getItemTitle().contains("fire and blood"));
    }

    @Test
    public void getItemAuthor() {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("George Martin");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getItemAuthor().contains("george r. r. martin"));
    }

    @Test
    public void readMore() {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("George Martin");
        itemDetailsPage = resultsPage.getItemDetailsPage();
    }

}

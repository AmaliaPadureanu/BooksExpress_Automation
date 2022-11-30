package testClasses;

import pages.NavigationPage;
import pages.SearchResultsPage;
import pages.SearchPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NavigationHistoryTests extends BaseTest {

    @BeforeClass
    public void userNavigationHistorySetup() {
        login();
    }

    @Test
    public void getUserNavigationHistoryTest() {
        String searchText = "It Starts with Us";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        navigationPage = new NavigationPage(driver);
        navigationHistoryPage = navigationPage.navigateToUserNavigationHistory();
        Assert.assertFalse(navigationHistoryPage.getNavigationHistory().isEmpty());
        Assert.assertTrue(navigationHistoryPage.getNavigationHistory().get(0).equals("It Starts with Us"));
    }
}

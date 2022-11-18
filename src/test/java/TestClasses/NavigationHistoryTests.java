package TestClasses;

import Pages.NavigationPage;
import Pages.SearchResultsPage;
import Pages.SearchPage;
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
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search("It Starts with Us");
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        navigationPage = new NavigationPage(driver);
        navigationHistoryPage = navigationPage.navigateToUserNavigationHistory();
        Assert.assertFalse(navigationHistoryPage.getNavigationHistory().isEmpty());
        Assert.assertTrue(navigationHistoryPage.getNavigationHistory().get(0).equals("It Starts with Us"));
    }
}

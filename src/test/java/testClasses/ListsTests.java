package testClasses;

import org.testng.annotations.AfterClass;
import pages.ListsPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.NavigationPage;
import utils.WaitUtils;

public class ListsTests extends BaseTest {

    @BeforeClass
    private void listsTestsSetup() {
        login();
    }

    @Test
    public void createListTest() {
        listsPage = new ListsPage(driver);
        listsPage.createList("bbb list", "private");
        driver.navigate().refresh();
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToLists();
        Assert.assertTrue(listsPage.getPageTitle().contains("Liste Express"));
        Assert.assertFalse(listsPage.getListsCreatedByUser().isEmpty());
        Assert.assertTrue(listsPage.getListsCreatedByUser().contains("bbb list"));
    }

    @Test
    public void searchInPublicListsTest() {
        String searchText = "software";
        navigationPage = new NavigationPage(driver);
        listsPage = navigationPage.navigateToPublicLists();
        WaitUtils.waitForUrlToBe(driver, "https://www.books-express.ro/lists", 5);
        Assert.assertTrue(listsPage.getPageTitle().contains("Liste Express"));
        Assert.assertTrue(listsPage.searchInPublicLists("software").toLowerCase().contains(searchText.toLowerCase()));
    }

    @AfterClass
    private void listsTestsTeardown() {
        logout();
    }
}

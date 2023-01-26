package testClasses;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ListsPage;
import pages.NavigationPage;
import utils.WaitUtils;

public class ListsTests extends BaseTest {
    @Test
    public void createListTest() {
        login();

        listsPage = new ListsPage(driver);
        listsPage.createList("y list", "private");
       ListsPage listsPage1 = new ListsPage(driver);
        System.out.println(listsPage1.getListsMenuOptions());
//        Assert.assertTrue(listsPage.getPageTitle().contains("Liste Express"));
//        Assert.assertFalse(listsPage.getListsCreatedByUser().isEmpty());
//        Assert.assertTrue(listsPage.getListsCreatedByUser().contains("y list"));
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
}

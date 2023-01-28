package testClasses;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.NavigationPage;
import utils.SeleniumUtils;

public class ListsTests extends BaseTest {
    @Test
    public void createListTest() {
        login();
        navigationPage = new NavigationPage(driver);
        listsPage = navigationPage.navigateToLists();
        Assert.assertTrue(listsPage.getPageTitle().contains("Liste Express"));
        listsPage.createList("ede list", "private");
        SeleniumUtils.refreshPage(driver);
        Assert.assertFalse(listsPage.getListsCreatedByUser().isEmpty());
        Assert.assertTrue(listsPage.getListsCreatedByUser().contains("ede list"));
    }
}

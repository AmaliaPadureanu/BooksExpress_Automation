package testClasses;

import org.testng.annotations.AfterClass;
import pages.ListsPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.NavigationPage;

public class ListsTests extends BaseTest {

    @BeforeClass
    private void listsTestsSetup() {
        login();
    }

    @Test
    public void createListTest() {
        listsPage = new ListsPage(driver);
        listsPage.createList("abc list");
        driver.navigate().refresh();
        navigationPage = new NavigationPage(driver);
        navigationPage.navigateToLists();
        //Assert.assertTrue(listsPage.getPageTitle().contains("Liste Express"));
        Assert.assertFalse(listsPage.getListsCreatedByUser().isEmpty());
        Assert.assertTrue(listsPage.getListsCreatedByUser().contains("abc list"));
    }

    @AfterClass
    private void listsTestsTeardown() {
        logout();
    }
}

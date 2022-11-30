package testClasses;

import pages.ListsPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ListsTests extends BaseTest {

    @BeforeClass
    private void listsTestsSetup() {
        login();
    }

    @Test
    public void createListTest() {
        listsPage = new ListsPage(driver);
        listsPage.createList("another list");
        driver.navigate().refresh();
        Assert.assertFalse(listsPage.getListsCreatedByUser().isEmpty());
        Assert.assertTrue(listsPage.getListsCreatedByUser().contains("another list"));
    }
}

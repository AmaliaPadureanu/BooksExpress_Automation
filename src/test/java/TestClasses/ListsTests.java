package TestClasses;

import Pages.ListsPage;
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
    public void createListTest() throws InterruptedException {
        listsPage = new ListsPage(driver);
        listsPage.createList("testing list");
        driver.navigate().refresh();
        Assert.assertFalse(listsPage.getListsCreatedByUser().isEmpty());
        Assert.assertTrue(listsPage.getListsCreatedByUser().contains("testing list"));
    }
}

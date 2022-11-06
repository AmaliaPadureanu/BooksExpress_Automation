package TestClasses;

import Pages.LoginPage;
import Pages.ResultsPage;
import Pages.SearchPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ItemDetailsTests extends BaseTest {

    @Test
    public void getItemTitleTest() {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("The Empire of Ashes");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getItemTitle().contains("the empire of ashes"));
    }

    @Test
    public void getItemAuthorTest() {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("The Legion of Flame");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getItemAuthor().contains("anthony ryan"));
    }

    @Test
    public void exapandCollapseItemDescriptionTest() throws InterruptedException {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("George Martin");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.readMore());
        Assert.assertTrue(itemDetailsPage.readLess());
    }

    @Test
    public void rateTest() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(email, password);
        Thread.sleep(2000);
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("The Song of Achilles");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.rate(3) == 4);
    }

}

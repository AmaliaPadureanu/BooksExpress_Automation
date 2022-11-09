package TestClasses;

import Pages.LoginPage;
import Pages.ResultsPage;
import Pages.SearchPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class ItemDetailsTests extends BaseTest {

    @Test
    @Ignore
    public void getItemTitleTest() {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("The Empire of Ashes");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getItemTitle().contains("the empire of ashes"));
    }

    @Test
    @Ignore
    public void getItemAuthorTest() {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("The Legion of Flame");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getItemAuthor().contains("anthony ryan"));
    }

    @Test
    @Ignore
    public void exapandCollapseItemDescriptionTest() throws InterruptedException {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("George Martin");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.readMore());
        Assert.assertTrue(itemDetailsPage.readLess());
    }

    @Test
    @Ignore
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

    @Test
    @Ignore
    public void seeAllByAuthor() throws InterruptedException {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("less is more");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.seeAllByAuthor().contains("Jason Hickel"));
    }

    @Test
    @Ignore
    public void seeAllByPublisher() throws InterruptedException {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("less is more");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.seeAllFromPublisher().contains("Random House"));
    }

    @Test
    public void writeReviewTest() {
        loginPage = new LoginPage(driver);
        loginPage.login();
        loginPage.logInWith(email, password);
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("ugly love");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        int nrOfReviewsBefore = itemDetailsPage.getNrOfReviews();
        itemDetailsPage.writeReview(2,true, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Enim nec dui nunc mattis enim ut tellus elementum sagittis.");
        int nrOfReviewsAfter = itemDetailsPage.getNrOfReviews();
        Assert.assertTrue(nrOfReviewsAfter == nrOfReviewsBefore + 1);
    }

}

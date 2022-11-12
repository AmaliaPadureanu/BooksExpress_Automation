package TestClasses;

import Pages.ResultsPage;
import Pages.SearchPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        login();
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("The Song of Achilles");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        itemDetailsPage.rate(3);
        Assert.assertTrue(getRating() == 4);
        logout();
    }

    @Test
    public void seeAllByAuthor() throws InterruptedException {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("less is more");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.seeAllByAuthor().contains("Jason Hickel"));
    }

    @Test
    public void seeAllByPublisher() throws InterruptedException {
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("less is more");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.seeAllFromPublisher().contains("Random House"));
    }

    @Test
    public void writeReviewTest() {
        login();
        searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("ugly love");
        itemDetailsPage = resultsPage.getItemDetailsPage();
        int nrOfReviewsBefore = itemDetailsPage.getNrOfReviews();
        itemDetailsPage.writeReview(2,true, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Enim nec dui nunc mattis enim ut tellus elementum sagittis.");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
        int nrOfReviewsAfter = itemDetailsPage.getNrOfReviews();
        Assert.assertTrue(nrOfReviewsAfter == nrOfReviewsBefore + 1);
        logout();
    }

    private int getRating() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(2000);
        List<WebElement> ratingGiven = driver.findElements(By.cssSelector(".stars>a[class$='fa fa-star gold']"));
        return ratingGiven.size();
    }

}

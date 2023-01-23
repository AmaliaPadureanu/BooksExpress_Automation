package testClasses;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ListsPage;
import pages.NavigationPage;
import pages.SearchPage;
import pages.SearchResultsPage;
import utils.WaitUtils;
import java.util.List;

public class ItemDetailsTests extends BaseTest {

    @BeforeClass
    public void setup() {
        login();
    }

    String searchText;

    @Test
    public void getItemTitleTest() {
        searchText = "The Empire of Ashes";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
    }

    @Test
    public void getItemAuthorTest() {
        searchText = "No Longer Human";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        Assert.assertTrue(itemDetailsPage.getItemAuthor().contains("Osamu Dazai"));
    }

    @Test
    public void exapandCollapseItemDescriptionTest() {
        searchText = "Fire and Blood";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        Assert.assertTrue(itemDetailsPage.readMore());
        Assert.assertTrue(itemDetailsPage.readLess());
    }

    @Test
    public void rateTest() {
        searchText = "Song of Achilles";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        itemDetailsPage.rate(3);
        driver.navigate().refresh();
        Assert.assertTrue(getRating() == 4);
    }

    @Test
    public void seeAllByAuthor() {
        searchText = "The Hobbit";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        itemDetailsPage.seeAllByAuthor();
        Assert.assertTrue(searchResultsPage.getPageTitle().contains("J. R. R. Tolkien"));
    }

    @Test
    public void seeAllByPublisher() {
        searchText = "Less is More";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        itemDetailsPage.seeAllFromPublisher();
        Assert.assertTrue(searchResultsPage.getPageTitle().contains("Random House"));
    }

    @Test
    public void writeReviewTest() {
        searchText = "Ugly Love";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        int nrOfReviewsBefore = itemDetailsPage.getNrOfReviews();
        itemDetailsPage.writeReview(2,true, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Enim nec dui nunc mattis enim ut tellus elementum sagittis.");
        driver.navigate().refresh();
        int nrOfReviewsAfter = itemDetailsPage.getNrOfReviews();
        Assert.assertTrue(nrOfReviewsAfter == nrOfReviewsBefore + 1);
    }

    @Test (dependsOnMethods = {"writeReviewTest"})
    public void editReviewTest() {
        searchText = "Ugly Love";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        itemDetailsPage.editReview(1,false, " edited text");
        driver.navigate().refresh();
        Assert.assertTrue(itemDetailsPage.getCommentContent().contains("edited text"));
    }

    @Test (priority = 1)
    public void removeReviewTest() {
        searchText = "Ugly Love";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        int nrOfReviewsBefore = itemDetailsPage.getNrOfReviews();
        itemDetailsPage.removeReview();
        driver.navigate().refresh();
        int nrOfReviewsAfter = itemDetailsPage.getNrOfReviews();
        Assert.assertTrue(nrOfReviewsAfter == nrOfReviewsBefore - 1);
    }

    private int getRating() {
        List<WebElement> ratingGiven = driver.findElements(By.cssSelector(".stars>a[class$='fa fa-star gold']"));
        WaitUtils.wait(driver, 3);
        return ratingGiven.size();
    }

    @Test
    public void addToListTest() {
        String listName = "x list";
        searchText = "The Song of Achilles";
        searchPage = new SearchPage(driver);
        listsPage = new ListsPage(driver);
        navigationPage = new NavigationPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().toLowerCase().contains(searchText.toLowerCase()));
        itemDetailsPage.addToList(listName);
        navigationPage.navigateToLists();
        Assert.assertTrue(listsPage.getPageTitle().contains("Liste Express"));
        Assert.assertTrue(listsPage.getItemsInList(listName).contains(searchText));
    }

    @AfterClass
    public void teardown() {
        logout();
    }
}

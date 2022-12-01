package testClasses;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ListsPage;
import pages.NavigationPage;
import pages.SearchPage;
import pages.SearchResultsPage;
import utils.WaitUtils;
import java.util.List;

public class ItemDetailsTests extends BaseTest {

    String searchText;

    @Test (enabled = false)
    public void getItemTitleTest() {
        searchText = "The Empire of Ashes";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
    }

    @Test (enabled = false)
    public void getItemAuthorTest() {
        searchText = "No Longer Human";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        Assert.assertTrue(itemDetailsPage.getItemAuthor().contains("Osamu Dazai"));
    }

    @Test (enabled = false)
    public void exapandCollapseItemDescriptionTest() {
        searchText = "Fire and Blood";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        Assert.assertTrue(itemDetailsPage.readMore());
        Assert.assertTrue(itemDetailsPage.readLess());
    }

    @Test (enabled = false)
    public void rateTest() {
        login();
        searchText = "Song of Achilles";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        itemDetailsPage.rate(3);
        driver.navigate().refresh();
        Assert.assertTrue(getRating() == 4);
        logout();
    }

    @Test (enabled = false)
    public void seeAllByAuthor() {
        searchText = "The Hobbit";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        itemDetailsPage.seeAllByAuthor();
        Assert.assertTrue(searchResultsPage.getPageTitle().contains("J. R. R. Tolkien"));
    }

    @Test (enabled = false)
    public void seeAllByPublisher() {
        searchText = "Less is More";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        itemDetailsPage.seeAllFromPublisher();
        Assert.assertTrue(searchResultsPage.getPageTitle().contains("Random House"));
    }

    @Test (enabled = false)
    public void writeReviewTest() {
        login();
        searchText = "Ugly Love";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        int nrOfReviewsBefore = itemDetailsPage.getNrOfReviews();
        itemDetailsPage.writeReview(2,true, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Enim nec dui nunc mattis enim ut tellus elementum sagittis.");
        driver.navigate().refresh();
        int nrOfReviewsAfter = itemDetailsPage.getNrOfReviews();
        Assert.assertTrue(nrOfReviewsAfter == nrOfReviewsBefore + 1);
        logout();
    }

    private int getRating() {
        driver.navigate().refresh();
        WaitUtils.wait(driver, 3);
        List<WebElement> ratingGiven = driver.findElements(By.cssSelector(".stars>a[class$='fa fa-star gold']"));
        WaitUtils.wait(driver, 3);
        return ratingGiven.size();
    }

    @Test
    public void addToListTest() {
        login();
        String listName = "abc list";
        searchText = "Song of Achilles";
        searchPage = new SearchPage(driver);
        listsPage = new ListsPage(driver);
        navigationPage = new NavigationPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        itemDetailsPage.addToList(listName);
        navigationPage.navigateToLists();
        Assert.assertTrue(listsPage.getPageTitle().contains("Liste Express"));
        Assert.assertTrue(listsPage.getItemsInList(listName).contains(searchText));
        logout();
    }
}

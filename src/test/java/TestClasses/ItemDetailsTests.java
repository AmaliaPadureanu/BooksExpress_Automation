package TestClasses;

import Pages.ListsPage;
import Pages.SearchPage;
import Pages.SearchResultsPage;
import Utils.WaitUtils;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ItemDetailsTests extends BaseTest {

    String searchText;

    @Test
    public void getItemTitleTest() {
        searchText = "The Empire of Ashes";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.getItemTitle().contains(searchText));
    }

    @Test
    public void getItemAuthorTest() {
        searchText = "No Longer Human";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.getItemAuthor().contains("Osamu Dazai"));
    }

    @Test
    public void exapandCollapseItemDescriptionTest() {
        searchText = "Fire and Blood";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.readMore());
        Assert.assertTrue(itemDetailsPage.readLess());
    }

    @Test
    public void rateTest() {
        login();
        searchText = "The Song of Achilles";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        itemDetailsPage.rate(3);
        driver.navigate().refresh();
        Assert.assertTrue(getRating() == 4);
        logout();
    }

    @Test
    public void seeAllByAuthor() {
        searchText = "The Atlas Six";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.seeAllByAuthor().contains("Olivie Blake"));
    }

    @Test
    public void seeAllByPublisher() {
        searchText = "Less is More";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        Assert.assertTrue(itemDetailsPage.seeAllFromPublisher().contains("Random House"));
    }

    @Test
    public void writeReviewTest() {
        login();
        searchText = "Ugly Love";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        int nrOfReviewsBefore = itemDetailsPage.getNrOfReviews();
        itemDetailsPage.writeReview(2,true, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Enim nec dui nunc mattis enim ut tellus elementum sagittis.");
        driver.navigate().refresh();
        int nrOfReviewsAfter = itemDetailsPage.getNrOfReviews();
        Assert.assertTrue(nrOfReviewsAfter == (nrOfReviewsBefore + 1));
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
        String listName = "listli";
        searchText = "The Song of Achilles";
        searchPage = new SearchPage(driver);
        listsPage = new ListsPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        driver.navigate().refresh();
        itemDetailsPage = searchResultsPage.getItemDetailsPage(searchText);
        itemDetailsPage.addToList(listName);
        Assert.assertTrue(listsPage.getItemsInList(listName).contains(searchText));
        logout();
    }
}

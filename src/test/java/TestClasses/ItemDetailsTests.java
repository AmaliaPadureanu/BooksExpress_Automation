package TestClasses;

import Pages.ListsPage;
import Pages.SearchResultsPage;
import Pages.SearchPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ItemDetailsTests extends BaseTest {

    String searchText;

    @Test
    public void getItemTitleTest() {
        searchText = "The Empire of Ashes";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getItemTitle().contains(searchText.toLowerCase()));
    }

    @Test
    public void getItemAuthorTest() {
        searchText = "The Legion of Flame";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getItemAuthor().contains("anthony ryan"));
    }

    @Test
    public void exapandCollapseItemDescriptionTest() {
        searchText = "George Martin";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.readMore());
        Assert.assertTrue(itemDetailsPage.readLess());
    }

    @Test
    public void rateTest() {
        searchText = "The Song of Achilles";
        login();
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        itemDetailsPage.rate(3);
        Assert.assertTrue(getRating() == 4);
        logout();
    }

    @Test
    public void seeAllByAuthor() {
        searchText = "less is more";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.seeAllByAuthor().contains("Jason Hickel"));
    }

    @Test
    public void seeAllByPublisher() {
        searchText = "less is more";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.seeAllFromPublisher().contains("Random House"));
    }

    @Test
    public void writeReviewTest() {
        searchText = "ugly love";
        login();
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        int nrOfReviewsBefore = itemDetailsPage.getNrOfReviews();
        itemDetailsPage.writeReview(2,true, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Enim nec dui nunc mattis enim ut tellus elementum sagittis.");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
        int nrOfReviewsAfter = itemDetailsPage.getNrOfReviews();
        Assert.assertTrue(nrOfReviewsAfter == (nrOfReviewsBefore + 1));
        logout();
    }

    private int getRating() {
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<WebElement> ratingGiven = driver.findElements(By.cssSelector(".stars>a[class$='fa fa-star gold']"));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return ratingGiven.size();
    }

    @Test
    public void addToListTest() throws InterruptedException {
        searchText = "The Song of Achilles";
        login();
        searchPage = new SearchPage(driver);
        listsPage = new ListsPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(2)"))
                .getText().toLowerCase().contains(searchText.toLowerCase()));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        itemDetailsPage.addToList("testtest");
        Assert.assertTrue(listsPage.getItemsInList("testtest").contains("The Song of Achilles"));
        logout();
    }

}

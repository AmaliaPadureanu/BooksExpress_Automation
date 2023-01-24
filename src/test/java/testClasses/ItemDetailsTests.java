package testClasses;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ListsPage;
import pages.NavigationPage;
import pages.SearchPage;
import pages.SearchResultsPage;
import utils.GenericUtils;

public class ItemDetailsTests extends BaseTest {

//    @BeforeClass
//    public void setup() {
//        login();
//    }

    String searchText;

    @Test
    public void getItemTitleTest() {
        searchText = "The Empire of Ashes";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
    }

    @Test
    public void getItemAuthorTest() {
        searchText = "No Longer Human";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
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
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        Assert.assertTrue(itemDetailsPage.readMore());
        Assert.assertTrue(itemDetailsPage.readLess());
    }

    @Test
    public void rateTest() {
        login();
        searchText = "Song of Achilles";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        itemDetailsPage.rate("5");
        driver.navigate().refresh();
        Assert.assertTrue(itemDetailsPage.getProductRating().equals("5"));
    }

    @Test
    public void seeAllByAuthorTest() {
        searchText = "The Hobbit";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        itemDetailsPage.seeAllByAuthor();
        Assert.assertTrue(searchResultsPage.getPageTitle().contains("J. R. R. Tolkien"));
    }

    @Test
    public void seeAllByPublisherTest() {
        searchText = "Less is More";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        itemDetailsPage.seeAllFromPublisher();
        Assert.assertTrue(searchResultsPage.getPageTitle().contains("Random House"));
    }

    @Test
    public void writeReviewTest() {
        login();
        searchText = "Ugly Love";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        int nrOfReviewsBefore = itemDetailsPage.getNrOfReviews();
        itemDetailsPage.writeReview("3",true, GenericUtils.createRandomString(200));
        Assert.assertTrue(itemDetailsPage.getNrOfReviews() == nrOfReviewsBefore + 1);
    }

    @Test
    public void editReviewTest() {
        login();
        searchText = "Ugly Love";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        itemDetailsPage.editReview("1",false, " edited text");
        Assert.assertTrue(itemDetailsPage.getReviewLeftByUserContent().contains("edited text"));
    }

    @Test
    public void removeReviewTest() {
        login();
        searchText = "Ugly Love";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getItemDetailsPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        int nrOfReviewsBefore = itemDetailsPage.getNrOfReviews();
        itemDetailsPage.removeReview();
        Assert.assertTrue(itemDetailsPage.getNrOfReviews() == nrOfReviewsBefore - 1);
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

//    @AfterClass
//    public void teardown() {
//        logout();
//    }
}

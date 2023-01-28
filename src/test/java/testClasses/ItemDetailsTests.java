package testClasses;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SearchPage;
import pages.SearchResultsPage;
import utils.GenericUtils;

public class ItemDetailsTests extends BaseTest {
    @BeforeClass
    public void beforeClass() {
        login();
    }

    String searchText;

    @Test
    public void getItemTitleTest() {
        searchText = "The Empire of Ashes";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getFirstItemPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
    }

    @Test
    public void getItemAuthorTest() {
        searchText = "No Longer Human";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getFirstItemPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        Assert.assertTrue(itemDetailsPage.getItemAuthor().contains("Osamu Dazai"));
    }

    @Test
    public void exapandCollapseItemDescriptionTest() {
        searchText = "Fire and Blood";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getFirstItemPage();
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
        itemDetailsPage = searchResultsPage.getFirstItemPage();
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
        itemDetailsPage = searchResultsPage.getFirstItemPage();
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
        itemDetailsPage = searchResultsPage.getFirstItemPage();
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
        itemDetailsPage = searchResultsPage.getFirstItemPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        int nrOfReviewsBefore = itemDetailsPage.getNrOfReviews();
        itemDetailsPage.writeReview("3",true, GenericUtils.createRandomString(200));
        Assert.assertTrue(itemDetailsPage.getNrOfReviews() == nrOfReviewsBefore + 1);
    }

    @Test (dependsOnMethods = {"writeReviewTest"})
    public void editReviewTest() {
        searchText = "Ugly Love";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getFirstItemPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        itemDetailsPage.editReview("1",false, " edited text");
        Assert.assertTrue(itemDetailsPage.getReviewLeftByUserContent().contains("edited text"));
    }

    @Test (dependsOnMethods = {"editReviewTest"})
    public void removeReviewTest() {
        searchText = "Ugly Love";
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search(searchText);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(searchText));
        itemDetailsPage = searchResultsPage.getFirstItemPage();
        Assert.assertTrue(itemDetailsPage.getPageTitle().contains(searchText));
        int nrOfReviewsBefore = itemDetailsPage.getNrOfReviews();
        itemDetailsPage.removeReview();
        Assert.assertTrue(itemDetailsPage.getNrOfReviews() == nrOfReviewsBefore - 1);
    }

    @Test
    public void notifyWhenProductIsBackInStockTest() {
        searchPage = new SearchPage(driver);
        SearchResultsPage searchResultsPage = searchPage.search("Innocent Foxes");
        itemDetailsPage = searchResultsPage.getFirstItemPage();
        Assert.assertEquals(itemDetailsPage.getProductAvailability(), "Carte indisponibilă temporar");
        itemDetailsPage.notifyWhenProductIsBackInStock("emipopesc@gmail.com", "3847859483");
        Assert.assertTrue(!itemDetailsPage.getNotifySuccessMessage().isBlank());
    }

}

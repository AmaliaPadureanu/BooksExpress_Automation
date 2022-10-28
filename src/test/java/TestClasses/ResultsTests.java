package TestClasses;

import Pages.ResultsPage;
import Pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResultsTests {

    WebDriver driver;
    String baseURL;

    @BeforeClass
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Amalia/IdeaProjects/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        Thread.sleep(1000);
        baseURL = "https://www.books-express.ro/";
        driver.get(baseURL);
    }

    @Test
    public void filterPriceInAscendingOrderTest() {
        SearchPage searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("George Martin");
        resultsPage.filterAscendingOrder();
    }

    @Test
    public void isAscending() {
        SearchPage searchPage = new SearchPage(driver);
        ResultsPage resultsPage = searchPage.search("George Martin");
        resultsPage.filterAscendingOrder();
        Assert.assertTrue(resultsPage.checkAscendingOrder());
    }
}

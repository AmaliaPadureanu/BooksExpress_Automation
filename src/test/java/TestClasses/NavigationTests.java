package TestClasses;

import Pages.NavigationPage;
import Pages.NewsletterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.PortUnreachableException;
import java.util.concurrent.TimeUnit;

public class NavigationTests {

    WebDriver driver;
    String baseURL;
    NavigationPage navigationPage;
    public String REDUCERI_URL = "https://www.books-express.ro/reduceri";
    public String TOP_VANZARI_URL = "https://www.books-express.ro/top/carti";
    public String NOUTATI_URL = "https://www.books-express.ro/carti?n=1";
    public String CADOURI_URL = "https://www.books-express.ro/carti/lifestyle-cadouri/wzg";
    public String BLOG_URL = "https://www.books-express.ro/blog/";
    public String NEWSLETTER_URL = "https://www.books-express.ro/newsletter";
    public String AFTER_SUBSCRIBE_MESSAGE = "//p[@class='align-center']";

    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Amalia/IdeaProjects/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        baseURL = "https://www.books-express.ro/";
        driver.get(baseURL);
    }

    @Test
    public void openReduceri() {
        navigationPage = new NavigationPage(driver);
        navigationPage.reduceri();
        Assert.assertTrue(driver.getCurrentUrl().equals(REDUCERI_URL));
    }

    @Test
    public void openTopVanzari() {
        navigationPage = new NavigationPage(driver);
        navigationPage.topVanzari();
        Assert.assertTrue(driver.getCurrentUrl().equals(TOP_VANZARI_URL));
    }

    @Test
    public void openNoutati() {
        navigationPage = new NavigationPage(driver);
        navigationPage.noutati();
        Assert.assertTrue(driver.getCurrentUrl().equals(NOUTATI_URL));
    }

    @Test
    public void openCadouri() {
        navigationPage = new NavigationPage(driver);
        navigationPage.cadouri();
        Assert.assertTrue(driver.getCurrentUrl().equals(CADOURI_URL));
    }
    @Test
    public void openBlog() {
        navigationPage = new NavigationPage(driver);
        navigationPage.blog();
        Assert.assertTrue(driver.getCurrentUrl().equals(BLOG_URL));
    }

    @Test
    public void openNewsletter() {
        navigationPage = new NavigationPage(driver);
        navigationPage.newsletter();
        Assert.assertTrue(driver.getCurrentUrl().equals(NEWSLETTER_URL));
    }

    @Test
    public void subscribeToNewsletterTest() throws InterruptedException {
        navigationPage = new NavigationPage(driver);
        NewsletterPage newsletterPage = navigationPage.newsletter();
        newsletterPage.subscribeToNewsletter("funnybunn@yaho.com", "Funny Bunny", "ger");
        Assert.assertNotNull(AFTER_SUBSCRIBE_MESSAGE);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}

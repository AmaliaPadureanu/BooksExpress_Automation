package pages;

import utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class NavigationPage {

    WebDriver driver;

    public NavigationPage(WebDriver driver) {
        this.driver = driver;
    }

    private String REDUCERI_LINK = "//a[@href='/reduceri']";
    private String TOP_VANZARI_LINK = "//a[@href='/top/carti']";
    private String NOUTATI_LINK = "//div[@id='submenu']//a[contains(text(),'Noutăți')]";
    private String CADOURI_LINK = "//a[normalize-space()='Cadouri']";
    private String BLOG_LINK = "//div[@id='submenu']//a[normalize-space()='Blog']";
    private String NEWSLETTER_LINK = "//a[contains(text(),'Abonează-te la newsletter!')]";
    private String CONTACT_LINK = "//a[normalize-space()='Contact']";
    private String PRODUCTS_LINK = "//a[@id='products']";
    private String BUSINESS_CATEGORY = "//a[@class='category-menu'][normalize-space()='Business']";
    private String CONT_LINK = "//a[@id='show-user']//i[@class='fa fa-angle-down']";
    private String PERSONAL_INFO = "//ul[@class='jq-dropdown-menu']//a[normalize-space()='Detalii personale']";
    private String NAVIGATION_HISTORY = "//a[normalize-space()='Istoric de navigare']";
    private String LISTS = "//span[normalize-space()='Liste']";
    private String WISHLIST_BTN = "//a[@href='/user/wishlist']";
    private String WISHLIST_LINK = "//a[@id='show-lists']//i[@class='fa fa-angle-down']";
    private String VEZI_COSUL_BTN = "//a[contains(text(),'Vezi coșul')]";
    private String COS_BTN = "//span[contains(text(),'Coș')]";

    public String selectProductsCategory() {
        Actions actions = new Actions(driver);

        WebElement products = driver.findElement(By.xpath(PRODUCTS_LINK));
        WebElement business = driver.findElement(By.xpath(BUSINESS_CATEGORY));

        actions.moveToElement(products).moveToElement(business).perform();
        WaitUtils.waitForElementToBeClickable(driver, By.partialLinkText("Economie"), 5);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Economie")));

        return driver.getTitle().toLowerCase();
    }

    public void navigateToSales() {
        driver.findElement(By.xpath(REDUCERI_LINK)).click();
    }

    public void navigateToTopSales() {
        driver.findElement(By.xpath(TOP_VANZARI_LINK)).click();
    }

    public void navigateToNovelties() {
        driver.findElement(By.xpath(NOUTATI_LINK)).click();
    }

    public void navigateToGifts() {
        driver.findElement(By.xpath(CADOURI_LINK)).click();
    }

    public void blog() {
        driver.findElement(By.xpath(BLOG_LINK)).click();
    }

    public NewsletterPage navigateToNewsletter() {
        driver.findElement(By.xpath(NEWSLETTER_LINK)).click();
        return new NewsletterPage(driver);
    }

    public ContactPage navigateToContact() {
        driver.findElement(By.xpath(CONTACT_LINK)).click();
        return new ContactPage(driver);
    }

    public UserDetailsPage navigateToUserDetails() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(CONT_LINK))).click(driver.findElement(By.xpath(PERSONAL_INFO))).perform();
        return new UserDetailsPage(driver);
    }

    public NavigationHistoryPage navigateToUserNavigationHistory() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(CONT_LINK))).click(driver.findElement(By.xpath(NAVIGATION_HISTORY))).perform();
        return new NavigationHistoryPage(driver);
    }

    public WishlistPage navigateToWishlist() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//a[@id='show-lists']//i[@class='fa fa-angle-down']")))
                .click(driver.findElement(By.xpath("//a[@href='/user/wishlist']//i[@class='fa fa-heart']"))).perform();
        return new WishlistPage(driver);
    }

    public ShoppingCartPage navigateToCart() {
        driver.findElement(By.xpath(COS_BTN)).click();
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(VEZI_COSUL_BTN), 5).click();
        return new ShoppingCartPage(driver);
    }

    public ListsPage navigateToLists() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("show-lists"))).perform();
        driver.findElement(By.linkText("Toate listele")).click();
        return new ListsPage(driver);
    }

}

package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.JavaScriptUtils;
import utils.WaitUtils;

public class NavigationPage extends BasePage {

    private By SALES_LINK = By.xpath("//a[@href='/reduceri']");
    private By TOP_SALES_LINK = By.xpath("//a[@href='/top/carti']");
    private By NEW_PRODUCTS_LINK = By.xpath("//div[@id='submenu']//a[contains(text(),'Noutăți')]");
    private By GIFTS_LINK = By.xpath("//a[normalize-space()='Cadouri']");
    private By BLOG_LINK = By.xpath("//div[@id='submenu']//a[normalize-space()='Blog']");
    private By NEWSLETTER_LINK = By.xpath("//a[contains(text(),'Abonează-te la newsletter!')]");
    private By CONTACT_LINK = By.xpath("//a[normalize-space()='Contact']");
    private By PRODUCTS_LINK = By.id("products");
    private By BUSINESS_CATEGORY = By.xpath("//a[@class='category-menu'][normalize-space()='Business']");
    private By ACCOUNT_LINK = By.xpath("//a[@id='show-user']//i[@class='fa fa-angle-down']");
    private By PERSONAL_INFO = By.xpath("//ul[@class='jq-dropdown-menu']//a[normalize-space()='Detalii personale']");
    private By NAVIGATION_HISTORY = By.xpath("//a[normalize-space()='Istoric de navigare']");
    private By LISTS = By.id("show-lists");
    private By SEE_ALL_LISTS = By.linkText("Toate listele");
    private By WISHLIST_LINK = By.cssSelector("a[href='/user/wishlist']");
    private By SEE_CART_BTN = By.xpath("//a[contains(text(),'Vezi coșul')]");
    private By CART_BTN = By.xpath("//span[contains(text(),'Coș')]");
    private By PUBLIC_LISTS = By.xpath("//b[contains(text(),'Explorează listele publice')]");

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    public String selectProductsCategory() {
        Actions actions = new Actions(getDriver());

        WebElement products = find(PRODUCTS_LINK);
        WebElement business = find(BUSINESS_CATEGORY);

        actions.moveToElement(products).moveToElement(business).perform();
        WaitUtils.waitForElementToBeClickable(getDriver(), By.partialLinkText("Economie"), 5);
        JavaScriptUtils.click(getDriver(), find(By.partialLinkText("Economie")));

        return getDriver().getTitle().toLowerCase();
    }

    public void navigateToSales() {
        find(SALES_LINK).click();
    }

    public void navigateToTopSales() {
        find(TOP_SALES_LINK).click();
    }

    public void navigateToNovelties() {
        find(NEW_PRODUCTS_LINK).click();
    }

    public void navigateToGifts() {
        find(GIFTS_LINK).click();
    }

    public void blog() {
        find(BLOG_LINK).click();
    }

    public NewsletterPage navigateToNewsletter() {
        find(NEWSLETTER_LINK).click();
        return new NewsletterPage(getDriver());
    }

    public ContactPage navigateToContact() {
        find(CONTACT_LINK).click();
        return new ContactPage(getDriver());
    }

    public UserDetailsPage navigateToUserDetails() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(find(ACCOUNT_LINK)).click(find(PERSONAL_INFO)).perform();
        return new UserDetailsPage(getDriver());
    }

    public NavigationHistoryPage navigateToUserNavigationHistory() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(find(ACCOUNT_LINK)).click(find(NAVIGATION_HISTORY)).perform();
        return new NavigationHistoryPage(getDriver());
    }

    public WishlistPage navigateToWishlist() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(find(LISTS))
                .click(find(WISHLIST_LINK)).perform();
        return new WishlistPage(getDriver());
    }

    public ShoppingCartPage navigateToCart() {
        find(CART_BTN).click();
        WaitUtils.waitForElementToBeClickable(getDriver(), SEE_CART_BTN, 5).click();
        return new ShoppingCartPage(getDriver());
    }

    public ListsPage navigateToLists() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(find(LISTS)).perform();
        find(SEE_ALL_LISTS).click();
        return new ListsPage(getDriver());
    }

    public ListsPage navigateToPublicLists() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(find(LISTS)).perform();
        click(PUBLIC_LISTS);
        return new ListsPage(getDriver());
    }

}

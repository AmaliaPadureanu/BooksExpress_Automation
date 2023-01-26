package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.JavaScriptUtils;

import java.time.Duration;

public class NavigationPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.ID, using = "show-user")
    private WebElement accountButton;
    @FindBy(how = How.CSS, using = "#user-data > ul > li:nth-child(1) > a")
    private WebElement enterAccountButton;
    @FindBy(how = How.XPATH, using = "//a[@href='/reduceri']")
    private WebElement salesButton;
    @FindBy(how = How.XPATH, using = "//a[@href='/top/carti']")
    private WebElement topSalesButton;
    @FindBy(how = How.XPATH, using = "//div[@id='submenu']//a[contains(text(),'Noutăți')]")
    private WebElement newProductsButton;
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Cadouri']")
    private WebElement giftsButton;
    @FindBy(how = How.XPATH, using = "//div[@id='submenu']//a[normalize-space()='Blog']")
    private WebElement blogButton;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Abonează-te la newsletter!')]")
    private WebElement newsletterButton;
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Contact']")
    private WebElement contactButton;
    private By PRODUCTS_LINK = By.id("products");
    private By BUSINESS_CATEGORY = By.xpath("//a[@class='category-menu'][normalize-space()='Business']");
    private By ACCOUNT_LINK = By.xpath("//a[@id='show-user']//i[@class='fa fa-angle-down']");
    private By PERSONAL_INFO = By.xpath("//ul[@class='jq-dropdown-menu']//a[normalize-space()='Detalii personale']");
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Istoric de navigare']")
    private WebElement navigationHistory;
    @FindBy(how = How.ID, using = "show-lists")
    private WebElement listsMenu;
    @FindBy(how = How.XPATH, using = "//*[@id=\"lists-data\"]/ul/li[11]/a")
    private WebElement allListsButton;
    private By WISHLIST_LINK = By.cssSelector("a[href='/user/wishlist']");
    private By SEE_CART_BTN = By.xpath("//a[contains(text(),'Vezi coșul')]");
    private By CART_BTN = By.xpath("//span[contains(text(),'Coș')]");
    private By PUBLIC_LISTS = By.xpath("//b[contains(text(),'Explorează listele publice')]");

    public NavigationPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public LoginPage navigateToLogin() {
        Actions actions = new Actions(driver);
        actions.moveToElement(accountButton).moveToElement(enterAccountButton).click().build().perform();
        return new LoginPage(driver);
    }

    public String selectProductsCategory() {
        Actions actions = new Actions(getDriver());

        WebElement products = find(PRODUCTS_LINK);
        WebElement business = find(BUSINESS_CATEGORY);

        actions.moveToElement(products).moveToElement(business).perform();
        //WaitUtils.waitForElementToBeClickable(getDriver(), By.partialLinkText("Economie"), 5);
        JavaScriptUtils.click(getDriver(), find(By.partialLinkText("Economie")));

        return getDriver().getTitle().toLowerCase();
    }

    public void navigateToSales() {
        salesButton.click();
    }

    public void navigateToTopSales() {
        topSalesButton.click();
    }

    public void navigateToNovelties() {
        newProductsButton.click();
    }

    public void navigateToGifts() {
        giftsButton.click();
    }

    public void blog() {
        blogButton.click();
    }

    public NewsletterPage navigateToNewsletter() {
        newsletterButton.click();
        return new NewsletterPage(driver);
    }

    public ContactPage navigateToContact() {
        contactButton.click();
        return new ContactPage(getDriver());
    }

    public UserDetailsPage navigateToUserDetails() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(find(ACCOUNT_LINK)).click(find(PERSONAL_INFO)).perform();
        return new UserDetailsPage(getDriver());
    }

    public NavigationHistoryPage navigateToUserNavigationHistory() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(accountButton).click(navigationHistory).perform();
        return new NavigationHistoryPage(getDriver());
    }

    public WishlistPage navigateToWishlist() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(listsMenu)
                .click(find(WISHLIST_LINK)).perform();
        return new WishlistPage(getDriver());
    }

    public ShoppingCartPage navigateToCart() {
        find(CART_BTN).click();
        //WaitUtils.waitForElementToBeClickable(getDriver(), SEE_CART_BTN, 5).click();
        return new ShoppingCartPage(getDriver());
    }

    public ListsPage navigateToLists() {
        Actions actions = new Actions(driver);
        actions.moveToElement(listsMenu).perform();
        allListsButton.click();
        return new ListsPage(driver);
    }

    public ListsPage navigateToPublicLists() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(listsMenu).perform();
        click(PUBLIC_LISTS);
        return new ListsPage(getDriver());
    }

}

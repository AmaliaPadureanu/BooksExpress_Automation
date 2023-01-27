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
    @FindBy(how = How.ID, using = "products")
    private WebElement productsLink;
    @FindBy(how = How.XPATH, using = "//a[@class='category-menu'][normalize-space()='Business']")
    private WebElement businessCategory;
    @FindBy(how = How.XPATH, using = "//a[@id='show-user']//i[@class='fa fa-angle-down']")
    private WebElement accountLink;
    @FindBy(how = How.XPATH, using = "//ul[@class='jq-dropdown-menu']//a[normalize-space()='Detalii personale']")
    private WebElement personalInfo;
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Istoric de navigare']")
    private WebElement navigationHistory;
    @FindBy(how = How.ID, using = "show-lists")
    private WebElement listsMenu;
    @FindBy(how = How.XPATH, using = "//*[@id=\"lists-data\"]/ul/li[11]/a")
    private WebElement allListsButton;
    @FindBy(how = How.CSS, using = "a[href='/user/wishlist']")
    private WebElement wishlistLink;
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Coș')]")
    private WebElement cartButton;
    @FindBy(how = How.XPATH, using = "//b[contains(text(),'Explorează listele publice')]")
    private WebElement publicLists;

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
        actions.moveToElement(productsLink).moveToElement(businessCategory).perform();
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
        actions.moveToElement(accountLink).click(personalInfo).perform();
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
                .click(wishlistLink).perform();
        return new WishlistPage(getDriver());
    }

    public ShoppingCartPage navigateToCart() {
        cartButton.click();
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
        publicLists.click();
        return new ListsPage(getDriver());
    }

}

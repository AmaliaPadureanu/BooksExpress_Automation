package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.GenericUtils;
import utils.JavaScriptUtils;
import utils.WaitUtils;
import java.time.Duration;
import java.util.AbstractCollection;
import java.util.List;
import java.util.stream.StreamSupport;

public class ItemDetailsPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//*[@id=\"main-row\"]/section[2]/section/div/div[3]/a[1]")
    private WebElement addToCartButton;
    @FindBy(how = How.XPATH, using = "//*[@id=\"main-row\"]/section[2]/section/div/div[3]/div/a[1]/i")
    private WebElement wishlistButton;
    @FindBy(how = How.XPATH, using = "//section[@id='book-main']//h1")
    private WebElement productTitle;
    @FindBy(how = How.CSS, using = "a[itemprop='author']")
    private WebElement productAuthor;
    @FindBy(how = How.XPATH, using = "//a[@class='read-more']")
    private WebElement readMoreButton;
    @FindBy(how = How.XPATH, using = "//a[@class='read-less']")
    private WebElement readLessButton;
    @FindBy(how = How.CSS, using = ".stars>a")
    private List<WebElement> rateStars;
    @FindBy(how = How.XPATH, using = "//a[@itemprop='publisher']")
    private WebElement productPublisher;
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Am citit!']")
    private WebElement reviewButton;
    @FindBy(how = How.ID, using = "review")
    private WebElement reviewTextInput;
    @FindBy(how = How.CSS, using = "div[id='book-rating'] div > a")
    private List<WebElement> reviewStars;
    @FindBy(how = How.CSS, using = "label[for='post_anon']")
    private WebElement postAnonymouslyCheckbox;
    @FindBy(how = How.ID, using = "saveReview")
    private WebElement saveReviewButton;
    @FindBy(how = How.XPATH, using = "//section[@id='product-reviews']//div//div")
    private List<WebElement> reviews;
    @FindBy(how = How.CSS, using = "body > div:nth-child(10) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > section:nth-child(2) > section:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(3)")
    private WebElement addToListDropdown;
    @FindBy(how = How.CSS, using = "#add2list-data > ul > li")
    private List<WebElement> lists;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Limba')]")
    private WebElement productLanguage;
    @FindBy(how = How.ID, using = "modify-review")
    private WebElement editReviewButton;
    @FindBy(how = How.CSS, using = "section[class='12u'] > section > div > div:last-child")
    private WebElement reviewLeftByCurrentUser;
    @FindBy(how = How.ID, using = "remove-review")
    private WebElement removeReviewButton;
    @FindBy(how = How.CSS, using = ".stars>a[class$='fa fa-star gold']")
    private List<WebElement> productRating;

    public ItemDetailsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean addToCart() {
        addToCartButton.click();
        return WaitUtils.waitForUrlToContain(getDriver(), "cart/added", 5);
    }

    public void addToWishlist() {
        wishlistButton.click();
    }

    public String getItemTitle() {
        return productTitle.getText();
    }

    public String getItemAuthor() {
        return productAuthor.getText();
    }

    public boolean readMore() {
        readMoreButton.click();
        WaitUtils.wait(driver, 5);
        if (readMoreButton.getAttribute("style").equals("display: none;")) {
           return true;
        }
        return false;
    }

    public boolean readLess() {
        JavaScriptUtils.click(driver, readLessButton);
        WaitUtils.wait(driver, 5);
        if (readLessButton.getAttribute("style").equals("display: none;")) {
            return true;
        }
        return false;
    }

    public void rate(String rating) {
        Actions actions = new Actions(driver);

        for (WebElement star : rateStars) {

            if (star.getAttribute("data-rating").equals(rating)) {
                actions.doubleClick(star).build().perform();
            }
        }
    }

    public void seeAllByAuthor() {
        productAuthor.click();
    }

    public void seeAllFromPublisher() {
        productPublisher.click();
    }

    public int getNrOfReviews() {
        return reviews.size();
    }

    public void writeReview(String rating, Boolean postAnonymous, String reviewText) {
        reviewButton.click();

        WaitUtils.wait(driver, 5);

        Actions actions = new Actions(driver);

        for (WebElement star : reviewStars) {
            if (star.getAttribute("data-rating").equals(rating)) {
                actions.doubleClick(star).build().perform();
            }
        }

        if (postAnonymous) {
           postAnonymouslyCheckbox.click();
        }

        reviewTextInput.sendKeys(reviewText);
        saveReviewButton.click();
    }

    public void editReview(String rating, Boolean postAnonymous, String reviewText) {
        editReviewButton.click();

        WaitUtils.wait(driver, 5);

        Actions actions = new Actions(driver);

        for (WebElement star : reviewStars) {
            if (star.getAttribute("data-rating").equals(rating)) {
                actions.doubleClick(star).build().perform();
            }
        }

        if (postAnonymous) {
            postAnonymouslyCheckbox.click();
        }

        reviewTextInput.sendKeys(reviewText);
        saveReviewButton.click();
    }

    public void removeReview() {
        removeReviewButton.click();
    }

    public String getReviewLeftByUserContent() {
        return reviewLeftByCurrentUser.getText();
    }

    public String getProductLanguage() {
        return productLanguage.getText();
    }

    public String getProductRating() {
        return String.valueOf(productRating.size());
    }
}

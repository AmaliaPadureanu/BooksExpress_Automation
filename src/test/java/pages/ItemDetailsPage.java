package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;
import java.time.Duration;
import java.util.List;

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
    @FindBy(how = How.XPATH, using = "//body/div[@id='main']/div[1]//header//h4")
    private WebElement productAvailability;
    @FindBy(how = How.ID, using = "email")
    private WebElement emailInput;
    @FindBy(how = How.ID, using = "phone")
    private WebElement telephoneInput;
    @FindBy(how = How.ID, using = "notification")
    private WebElement sendButton;
    @FindBy(how = How.ID, using = "notification-success")
    private WebElement notifySuccessMessage;

    public ItemDetailsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public ShoppingCartPage addToCart() {
        addToCartButton.click();
        return new ShoppingCartPage(driver);
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
        readLessButton.click();
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

    public String getProductAvailability() {
        return productAvailability.getText();
    }

    public void notifyWhenProductIsBackInStock(String email, String phone) {
        emailInput.sendKeys(email);
        telephoneInput.sendKeys(phone);
        sendButton.click();
    }

    public String getNotifySuccessMessage() {
        return notifySuccessMessage.getText();
    }
}

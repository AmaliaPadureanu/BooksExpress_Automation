package pages;

import utils.JavaScriptUtils;
import utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ItemDetailsPage extends BasePage {
    private By ADD_TO_CART_BTN = By.xpath("//a[@class='add2cart danger button full icon fa-basket']");
    private By WISHLIST_BTN = By.xpath("//a[@class='4u plain align-center add2list-btn']//i[@class='fa fa-heart']");
    private By ITEM_TITLE = By.cssSelector("section[id='book-main'] span[itemprop='name']");
    private By ITEM_AUTHOR = By.cssSelector("a[itemprop='author']");
    private By READ_MORE_BTN = By.xpath("//a[@class='read-more']");
    private By READ_LESS_BTN = By.xpath("//a[@class='read-less']");
    private By RATE_STARS = By.cssSelector(".stars>a");
    private By AUTHOR_LINK = By.xpath("//a[@itemprop='author']");
    private By PUBLISHER_LINK = By.xpath("//a[@itemprop='publisher']");
    private By ACCEPT_COOKIES_BTN = By.xpath("//a[contains(text(),'Accept cookie-uri')]");
    private By REVIEW_BTN = By.xpath("//a[normalize-space()='Am citit!']");
    private By TEXT_INPUT =  By.id("review");
    private By RATE_STARS_POPUP = By.xpath("//div[@class='box lightbox']//a[@class='fa fa-star']");
    private By POST_ANONYMOUSLY_CHECKBOX = By.xpath("//input[@id='post_anon'][@class='small']");
    private By SAVE_REVIEW_BTN = By.id("saveReview");
    private By REVIEWS = By.xpath("//section[@id='product-reviews']//div[@class='read-text serif']//h4[contains(.,'a dat nota: ')]");
    private By LISTS_DROPDOWN = By.xpath("//i[@class='fa fa-down-open icon-right']");
    private By LISTS = By.xpath("//a[@class='add2this-list']");
    private By PRODUCT_LANGUAGE = By.xpath("//a[contains(text(),'Limba')]");
    private By EDIT_REVIEW_BUTTON = By.id("modify-review");
    private By COMMENTS_SECTION = By.cssSelector("section[class='12u']");
    private By REMOVE_REVIEW_BUTTON = By.id("remove-review");

    public ItemDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean addToCart() {
        find(ADD_TO_CART_BTN).click();
        return WaitUtils.waitForUrlToContain(getDriver(), "cart/added", 5);
    }

    public void addToWishlist() {
        WebElement wishlistButton = find(WISHLIST_BTN);
        wishlistButton.click();
    }

    public String getItemTitle() {
        String itemTitle = find(ITEM_TITLE).getText();
        return itemTitle;
    }

    public String getItemAuthor() {
        String authorName = find(ITEM_AUTHOR).getText();
        return authorName;
    }

    public boolean readMore() {
        find(READ_MORE_BTN).click();
        WaitUtils.wait(getDriver(), 5);
        if (find(READ_MORE_BTN).getAttribute("style").equals("display: none;")) {
           return true;
        }
        return false;
    }

    public boolean readLess() {
        JavaScriptUtils.click(getDriver(), find(READ_LESS_BTN));
        WaitUtils.wait(getDriver(), 5);
        if (find(READ_LESS_BTN).getAttribute("style").equals("display: none;")) {
            return true;
        }
        return false;
    }

    public void rate(int rating) {
        List<WebElement> stars = findAll(RATE_STARS);
        stars.get(rating).click();
        WaitUtils.wait(getDriver(), 5);
    }

    public void seeAllByAuthor() {
        find(AUTHOR_LINK).click();
    }

    public void seeAllFromPublisher() {
        find(ACCEPT_COOKIES_BTN).click();
        find(PUBLISHER_LINK).click();
    }

    public int getNrOfReviews() {
        return findAll(REVIEWS).size();
    }

    public void writeReview(Integer rating, Boolean postAnonymous, String reviewText) {
        find(REVIEW_BTN).click();
        getDriver().switchTo().activeElement();

        List<WebElement> stars = findAll(RATE_STARS_POPUP);
        stars.get(rating).click();

        if (postAnonymous) {
            JavaScriptUtils.click(getDriver(), find(POST_ANONYMOUSLY_CHECKBOX));
        }

        type(TEXT_INPUT, reviewText);
        find(SAVE_REVIEW_BTN).click();
    }

    public void editReview(Integer rating, Boolean postAnonymous, String reviewText) {
        JavaScriptUtils.click(getDriver(), find(EDIT_REVIEW_BUTTON));
        getDriver().switchTo().activeElement();

        List<WebElement> stars = findAll(RATE_STARS_POPUP);
        stars.get(rating).click();

        if (postAnonymous) {
            JavaScriptUtils.click(getDriver(), find(POST_ANONYMOUSLY_CHECKBOX));
        }

        type(TEXT_INPUT, reviewText);
        find(SAVE_REVIEW_BTN).click();
    }

    public void removeReview() {
        JavaScriptUtils.click(getDriver(), find(REMOVE_REVIEW_BUTTON));
    }

    public void addToList(String listName) {
        JavaScriptUtils.click(getDriver(), find(LISTS_DROPDOWN));

        List<WebElement> lists = findAll(LISTS);

        for (WebElement list : lists) {
            if (list.getText().equals(listName)) {
                list.click();
            }
        }

        getDriver().navigate().refresh();
    }

    public String getCommentContent() {
        return getText(COMMENTS_SECTION);
    }

    public String getProductLanguage() {
        return getText(PRODUCT_LANGUAGE);
    }
}

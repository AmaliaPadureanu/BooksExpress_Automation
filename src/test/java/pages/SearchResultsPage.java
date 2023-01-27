package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.GenericUtils;
import utils.JavaScriptUtils;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResultsPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//div[@class='row list']//article//header//h2//a")
    private List<WebElement> listOfProducts;
    @FindBy(how = How.XPATH, using = "//i[@class='fa fa-down-open icon-right']")
    private WebElement filterDropdown;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Preț crescător')]")
    private WebElement ascendingPriceButton;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Preț descrescător')]")
    private WebElement descendingPriceButton;
    @FindBy(how = How.CSS, using = "#results-list > div > article:nth-child(1) > header > h2 > a > span")
    private WebElement firstProductInList;
    @FindBy(how = How.XPATH, using = "//b[@class='color-theme-5']")
    private List<WebElement> itemsOnResultsPage;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Engleză')]")
    private WebElement englishFilter;
    @FindBy(how = How.XPATH, using = "//a[@rel='nofollow'][contains(text(),'Germană')]")
    private WebElement germanFilter;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Spaniolă')]")
    private WebElement spanishFilter;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Italiană')]")
    private WebElement italianFilter;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Franceză')]")
    private WebElement frenchFilter;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public ItemDetailsPage getRandomProduct() {
        listOfProducts.get(GenericUtils.getRandomNumber(0, 90)).click();
        return new ItemDetailsPage(driver);
    }

    public ItemDetailsPage getItemDetailsPage() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .until((WebDriver driver) -> {
                    firstProductInList.click();
                    return true;
                });
        return new ItemDetailsPage(driver);
    }

    public void filterAscendingOrder() {
        filterDropdown.click();
        ascendingPriceButton.click();
    }

    public void filterDescendingOrder() {
        filterDropdown.click();
        descendingPriceButton.click();
    }

    public boolean checkAscendingOrder() {

        List<Integer> prices = new ArrayList<>();

        for (WebElement item : itemsOnResultsPage) {
            String price = item.getText().replace(" lei", "")
                    .replace(item.findElement(By.xpath("./sup")).getText(), "");
            try {
                prices.add(Integer.valueOf(price));
            } catch (NumberFormatException e) {}
        }

        List<Integer> pricesOrdered = new ArrayList<>(prices);
        Collections.sort(pricesOrdered);

        return prices.equals(pricesOrdered);
    }

    public boolean checkDescendingOrder() {
        List<Integer> prices = new ArrayList<>();

        for (WebElement item : itemsOnResultsPage) {
            String price = item.getText().replace(" lei", "");
            String p = price.substring(0, price.length() - 2);

            try {
                prices.add(Integer.valueOf(p));
            } catch (NumberFormatException e) {}
        }

        List<Integer> pricesOrdered = new ArrayList<>(prices);
        pricesOrdered.sort(Collections.reverseOrder());

        return prices.equals(pricesOrdered);
    }

    public void filterByLanguage(String language) {
        switch (language) {
            case "eng" -> JavaScriptUtils.click(driver, englishFilter);
            case "ger" -> JavaScriptUtils.click(driver, germanFilter);
            case "spa" -> JavaScriptUtils.click(driver, spanishFilter);
            case "fra" -> JavaScriptUtils.click(driver, frenchFilter);
            case "ita" -> JavaScriptUtils.click(driver, italianFilter);
        }
    }
}

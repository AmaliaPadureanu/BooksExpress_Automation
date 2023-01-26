package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.JavaScriptUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResultsPage extends BasePage {

    private String URL = "search?q=";
    private By FILTER_DROPDOWN = By.xpath("//i[@class='fa fa-down-open icon-right']");
    private By ASCENDING_PRICE_BTN = By.xpath("//a[contains(text(),'Preț crescător')]");
    private By DESCENDING_PRICE_BTN = By.xpath("//a[contains(text(),'Preț descrescător')]");
    private By FIRST_PRODUCT_IN_SEARCH_LIST = By.cssSelector("#results-list > div > article:nth-child(1) > header > h2 > a > span");
    private By ITEMS_ON_RESULTS_PAGE = By.xpath("//b[@class='color-theme-5']");
    private By ENGLISH_FILTER = By.xpath("//a[contains(text(),'Engleză')]");
    private By GERMAN_FILTER = By.xpath("//a[@rel='nofollow'][contains(text(),'Germană')]");
    private By SPANISH_FILTER = By.xpath("//a[contains(text(),'Spaniolă')]");
    private By ITALIAN_FILTER = By.xpath("//a[contains(text(),'Italiană')]");
    private By FRENCH_FILTER = By.xpath("//a[contains(text(),'Franceză')]");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpen() {
        return getDriver().getCurrentUrl().contains(URL);
    }

    public ItemDetailsPage getItemDetailsPage() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .until((WebDriver driver) -> {
                    driver.findElement(FIRST_PRODUCT_IN_SEARCH_LIST).click();
                    return true;
                });
        return new ItemDetailsPage(driver);
    }

    public void filterAscendingOrder() {
        click(FILTER_DROPDOWN);
        click(ASCENDING_PRICE_BTN);
    }

    public void filterDescendingOrder() {
        click(FILTER_DROPDOWN);
        click(DESCENDING_PRICE_BTN);
    }

    public boolean checkAscendingOrder() {
        List<WebElement> items = findAll(ITEMS_ON_RESULTS_PAGE);

        List<Integer> prices = new ArrayList<>();

        for (WebElement item : items) {
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
        List<WebElement> items = findAll(ITEMS_ON_RESULTS_PAGE);
        List<Integer> prices = new ArrayList<>();

        for (WebElement item : items) {
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
            case "eng" -> JavaScriptUtils.click(getDriver(), find(ENGLISH_FILTER));
            case "ger" -> JavaScriptUtils.click(getDriver(), find(GERMAN_FILTER));
            case "spa" -> JavaScriptUtils.click(getDriver(), find(SPANISH_FILTER));
            case "fra" -> JavaScriptUtils.click(getDriver(), find(FRENCH_FILTER));
            case "ita" -> JavaScriptUtils.click(getDriver(), find(ITALIAN_FILTER));
        }
    }
}

package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResultsPage extends BasePage {

    private String URL = "search?q=";
    private By FILTER_DROPDOWN = By.xpath("//i[@class='fa fa-down-open icon-right']");
    private By ASCENDING_PRICE_BTN = By.xpath("//a[contains(text(),'Preț crescător')]");
    private By DESCENDING_PRICE_BTN = By.xpath("//a[contains(text(),'Preț descrescător')]");
    private By SEARCH_ITEMS = By.xpath("//article//header//h2//a//span");
    private By ITEMS_ON_RESULTS_PAGE = By.xpath("//b[@class='color-theme-5']");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpen() {
        return getDriver().getCurrentUrl().contains(URL);
    }

    public ItemDetailsPage getItemDetailsPage(String itemName) {
        List<WebElement> searchItems = findAll(SEARCH_ITEMS);
        WebElement product = searchItems.stream()
                .filter(item -> item.getText().contains(itemName)).findFirst().get();
        product.click();
        return new ItemDetailsPage(getDriver());
    }

    public void filterAscendingOrder() {
        find(FILTER_DROPDOWN).click();
        find(ASCENDING_PRICE_BTN).click();
    }

    public void filterDescendingOrder() {
        find(FILTER_DROPDOWN).click();
        find(DESCENDING_PRICE_BTN).click();
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
}

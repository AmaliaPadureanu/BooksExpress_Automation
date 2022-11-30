package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResultsPage extends BasePage {

    WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    private String URL = "search?q=";
    private String FILTER_DROPDOWN = "//i[@class='fa fa-down-open icon-right']";
    private String ASCENDING_PRICE_BTN = "//a[contains(text(),'Preț crescător')]";
    private String DESCENDING_PRICE_BTN = "//a[contains(text(),'Preț descrescător')]";

    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL);
    }

    public ItemDetailsPage getItemDetailsPage(String itemName) {
        List<WebElement> searchItems = driver.findElements(By.xpath("//article//header//h2//a//span"));
        WebElement product = searchItems.stream()
                .filter(item -> item.getText().contains(itemName)).findFirst().get();
        product.click();
        return new ItemDetailsPage(driver);
    }

    public void filterAscendingOrder() {
        driver.findElement(By.xpath(FILTER_DROPDOWN)).click();
        driver.findElement(By.xpath(ASCENDING_PRICE_BTN)).click();
    }

    public void filterDescendingOrder() {
        driver.findElement(By.xpath(FILTER_DROPDOWN)).click();
        driver.findElement(By.xpath(DESCENDING_PRICE_BTN)).click();
    }

    public boolean checkAscendingOrder() {
        List<WebElement> items = driver.findElements(By.xpath("//b[@class='color-theme-5']"));

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
        List<WebElement> items = driver.findElements(By.xpath("//b[@class='color-theme-5']"));
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

    @Override
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Override
    public String getPageURL() {
        return driver.getCurrentUrl();
    }
}

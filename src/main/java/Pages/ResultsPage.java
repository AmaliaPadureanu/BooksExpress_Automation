package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultsPage {

    WebDriver driver;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String URL = "search?q=";
    public String ITEM_TO_BE_ADDED = "//article/descendant::a[1]";
    public String FILTER_DROPDOWN = "//i[@class='fa fa-down-open icon-right']";
    public String ASCENDING_PRICE_BTN = "//a[contains(text(),'Preț crescător')]";
    public String DESCENDING_PRICE_BTN = "//a[contains(text(),'Preț descrescător')]";

    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL);
    }

    public ItemDetailsPage getItemDetailsPage() {
        WebElement item = driver.findElement(By.xpath(ITEM_TO_BE_ADDED));
        item.click();
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
        List<WebElement> items = driver.findElements(By.cssSelector("article"));
        List<Integer> prices = new ArrayList<>();
        List<Integer> pricesOrdered = new ArrayList<>(prices);

        for (WebElement item : items) {
            String price = item.findElement(By.className("color-theme-5")).getText().replace(" lei", "");
            prices.add(Integer.valueOf(price));
        }

        Collections.sort(pricesOrdered);

        return prices.equals(pricesOrdered);
    }

    public boolean checkDescendingOrder() {
        List<WebElement> items = driver.findElements(By.cssSelector("article"));
        List<Integer> prices = new ArrayList<>();
        List<Integer> pricesOrdered = new ArrayList<>(prices);

        for (WebElement item : items) {
            String price = item.findElement(By.className("color-theme-5")).getText().replace(" lei", "");
            prices.add(Integer.valueOf(price));
        }

        Collections.sort(pricesOrdered, Collections.reverseOrder());

        return prices.equals(pricesOrdered);
    }

}

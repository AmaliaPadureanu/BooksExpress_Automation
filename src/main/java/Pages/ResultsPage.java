package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultsPage {

    WebDriver driver;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String URL = "search?q=";
    public String ITEM_TO_BE_ADDED = "//article/descendant::a[1]";

    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL);
    }

    public ItemDetailsPage getItemDetailsPage() {
        WebElement item = driver.findElement(By.xpath(ITEM_TO_BE_ADDED));
        item.click();
        return new ItemDetailsPage(driver);
    }
}

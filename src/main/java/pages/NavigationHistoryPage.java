package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class NavigationHistoryPage extends BasePage {

    WebDriver driver;

    public NavigationHistoryPage(WebDriver driver) {
        this.driver = driver;
    }

    private String ARTICLES_HEADERS = "//article//h2";

    public List<String> getNavigationHistory() {
        List<String> titles = new ArrayList<>();
        List<WebElement> articlesHeaders = driver.findElements(By.xpath(ARTICLES_HEADERS));

        for (WebElement articleHeader : articlesHeaders) {
            titles.add(articleHeader.getText());
        }
        return titles;
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

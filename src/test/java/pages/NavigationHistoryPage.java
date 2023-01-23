package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class NavigationHistoryPage extends BasePage {

    private By ARTICLES_HEADERS = By.xpath("//article//h2");

    public NavigationHistoryPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getNavigationHistory() {
        List<String> titles = new ArrayList<>();
        List<WebElement> articlesHeaders = findAll(ARTICLES_HEADERS);

        for (WebElement articleHeader : articlesHeaders) {
            titles.add(articleHeader.getText());
        }
        return titles;
    }
}

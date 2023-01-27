package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class NavigationHistoryPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//article//h2")
    private List<WebElement> articlesHeaders;

    public NavigationHistoryPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public List<String> getNavigationHistory() {
        List<String> titles = new ArrayList<>();

        for (WebElement articleHeader : articlesHeaders) {
            titles.add(articleHeader.getText());
        }
        return titles;
    }
}

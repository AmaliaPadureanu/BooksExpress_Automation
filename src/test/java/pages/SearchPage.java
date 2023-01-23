package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {

    private By SEARCH_BAR = By.id("search");
    private By SEARCH_BTN = By.xpath("//button[@type='submit']");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public SearchResultsPage search(String item) {
        click(SEARCH_BAR);
        clear(SEARCH_BAR);
        type(SEARCH_BAR, item);
        click(SEARCH_BTN);
        return new SearchResultsPage(getDriver());
    }
}

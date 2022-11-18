package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    private String SEARCH_BAR = "//input[@id='search']";
    private String SEARCH_BTN = "//button[@type='submit']";

    public SearchResultsPage search(String item) {
        WebElement searchBar = driver.findElement(By.xpath(SEARCH_BAR));
        searchBar.click();
        searchBar.clear();
        searchBar.sendKeys(item);
        driver.findElement(By.xpath(SEARCH_BTN)).click();
        return new SearchResultsPage(driver);
    }




}

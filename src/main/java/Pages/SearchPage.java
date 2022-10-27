package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class SearchPage {

    WebDriver driver;

    public String SEARCH_BAR = "//input[@id='search']";
    public String SEARCH_BTN = "//button[@type='submit']";

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public ResultsPage search(String item) {
        WebElement searchBar = driver.findElement(By.xpath(SEARCH_BAR));
        searchBar.click();
        searchBar.sendKeys("George Martin");
        driver.findElement(By.xpath(SEARCH_BTN)).click();
        return new ResultsPage(driver);

    }




}

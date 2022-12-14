package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    public void click(By locator) {
        find(locator).click();
    }

    public void clear(By locator) {
        find(locator).clear();
    }

    public void type(By locator, String text) {
        find(locator).sendKeys(text);
    }

    public String getText(By locator) {
        return find(locator).getText();
    }
}

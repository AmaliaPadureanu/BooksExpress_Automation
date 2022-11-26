package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ListsPage {

    WebDriver driver;

    public ListsPage(WebDriver driver) {
        this.driver = driver;
    }

    private String LISTS = "//span[normalize-space()='Liste']";
    private String CREATE_LIST_OPTION = "//a[contains(text(),'Creează o listă')]";
    private String TITLE_INPUT = "//input[@id='list_new_title']";
    private String CREATE_BTN = "//a[@id='list-new-create']";
    private String LISTS_CREATED_BY_USER = "//div[@id='lists-data']//ul[@class='jq-dropdown-menu']//li[@class='user-list']/a";
    private String ITEMS_IN_LIST = "//ul[@id='list-items']//li//h4//a";


    public void createList(String listName) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(LISTS)))
                .click(driver.findElement(By.xpath(CREATE_LIST_OPTION))).perform();
        driver.switchTo().activeElement();
        driver.findElement(By.xpath(TITLE_INPUT)).sendKeys(listName);
        driver.findElement(By.xpath(CREATE_BTN)).click();
    }

    public List<String> getListsCreatedByUser() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(LISTS)))
                .click(driver.findElement(By.linkText("Toate listele"))).perform();

        List<String> listsNames = new ArrayList<>();
        List<WebElement> lists = driver.findElements(By.xpath("//h4//a"));

        for (WebElement list : lists) {
            listsNames.add(list.getText());
        }
        return listsNames;
    }

    public List<String> getItemsInList(String listName) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(LISTS)))
                .click(driver.findElement(By.xpath("//a[normalize-space()='Toate listele']"))).perform();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[normalize-space()='" + listName + "']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        List<WebElement> items = driver.findElements(By.cssSelector("div[class='cart-details'] h4 a"));

        List<String> itemsInList = new ArrayList<>();

        for (WebElement item : items) {
            itemsInList.add(item.getText().substring(3));
        }

        return itemsInList;
    }
}

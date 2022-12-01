package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;

public class ListsPage extends BasePage {

    private By LISTS = By.xpath("//span[normalize-space()='Liste']");
    private By CREATE_LIST_OPTION = By.xpath("//a[contains(text(),'Creează o listă')]");
    private By TITLE_INPUT = By.id("list_new_title");
    private By CREATE_BTN = By.id("list-new-create");
    private By LISTS_CREATED_BY_USER = By.xpath("//h4//a");

    private By ITEMS_IN_LIST = By.cssSelector("div[class='cart-details'] h4 a");

    public ListsPage(WebDriver driver) {
        super(driver);
    }

    public void createList(String listName) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(find(LISTS)).click(find(CREATE_LIST_OPTION)).perform();
        getDriver().switchTo().activeElement();
        type(TITLE_INPUT, listName);
        find(CREATE_BTN).click();
    }

    public List<String> getListsCreatedByUser() {
        List<String> listsNames = new ArrayList<>();
        List<WebElement> lists = findAll(LISTS_CREATED_BY_USER);

        for (WebElement list : lists) {
            listsNames.add(list.getText());
        }
        return listsNames;
    }

    public List<String> getItemsInList(String listName) {
        find(By.xpath("//a[normalize-space()='" + listName + "']")).click();
        List<WebElement> items = findAll(ITEMS_IN_LIST);
        List<String> itemsInList = new ArrayList<>();

        for (WebElement item : items) {
            itemsInList.add(item.getText().substring(3));
        }

        return itemsInList;
    }
}

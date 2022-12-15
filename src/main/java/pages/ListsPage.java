package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.JavaScriptUtils;
import java.util.ArrayList;
import java.util.List;

public class ListsPage extends BasePage {

    private By LISTS = By.xpath("//span[normalize-space()='Liste']");
    private By CREATE_LIST_OPTION = By.xpath("//a[contains(text(),'Creează o listă')]");
    private By TITLE_INPUT = By.id("list_new_title");
    private By CREATE_BTN = By.id("list-new-create");
    private By LISTS_CREATED_BY_USER = By.xpath("//h4//a");
    private By ITEMS_IN_LIST = By.cssSelector("div[class='cart-details'] h4 a");
    private By PUBLIC_LISTS_SEARCHBAR = By.id("search-public-list");
    private By PUBLIC_LIST_SEARCHBUTTON = By.id("search-button");
    private By FIRST_LIST_IN_PUBLIC_LISTS = By.cssSelector("body > div:nth-child(10) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > h4:nth-child(3) > a:nth-child(1)");
    private By PUBLIC_LIST_RADIOBUTTON = By.xpath("//label[contains(text(),'Publică')]");
    private By PRIVATE_LIST_RADIOBUTTON = By.xpath("//label[contains(text(),'Privată')]");

    public ListsPage(WebDriver driver) {
        super(driver);
    }

    public void createList(String listName, String listVisibility) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(find(LISTS)).click(find(CREATE_LIST_OPTION)).perform();
        getDriver().switchTo().activeElement();
        type(TITLE_INPUT, listName);
        if (listVisibility.equals("public")) {
            click(PUBLIC_LIST_RADIOBUTTON);
        } else if (listVisibility.equals("private")){
            click(PRIVATE_LIST_RADIOBUTTON);
        } else {
            System.out.println("Please choose between public and private");
        }
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
        JavaScriptUtils.click(getDriver(), find(By.xpath("(//a[contains(text(),'" + listName + "')])[1]")));
        List<WebElement> items = findAll(ITEMS_IN_LIST);
        List<String> itemsInList = new ArrayList<>();

        for (WebElement item : items) {
            itemsInList.add(item.getText().substring(3));
        }

        return itemsInList;
    }

    public String searchInPublicLists(String listName) {
        type(PUBLIC_LISTS_SEARCHBAR, listName);
        click(PUBLIC_LIST_SEARCHBUTTON);
        return getText(FIRST_LIST_IN_PUBLIC_LISTS);
    }

}

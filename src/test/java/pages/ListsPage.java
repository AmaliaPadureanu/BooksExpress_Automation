package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ListsPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.ID, using = "show-lists")
    private WebElement listsMenu;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Creează o listă')]")
    private WebElement createListButton;
    @FindBy(how = How.ID, using = "list_new_title")
    private WebElement listTitleInput;
    @FindBy(how = How.ID, using = "list-new-create")
    private WebElement createButton;
    @FindBy(how = How.XPATH, using = "//h4//a")
    private List<WebElement> listsCreatedByUser;
    @FindBy(how = How.CSS, using = "div[class='cart-details'] h4 a")
    private List<WebElement> itemsInList;
    @FindBy(how = How.ID, using = "search-public-list")
    private WebElement publicListsSearchbar;
    @FindBy(how = How.ID, using = "search-button")
    private WebElement publicListsSearchButton;
    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Publică')]")
    private WebElement publicListRadiobutton;
    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Privată')]")
    private WebElement privateListRadiobutton;
    @FindBy(how = How.CSS, using = "div[id='lists-data'] ul > li > a")
    private List<WebElement> listsMenuOptions;

    public ListsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void createList(String listName, String listVisibility) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(listsMenu).click(createListButton).build().perform();
        WaitUtils.wait(driver, 5);
        listTitleInput.sendKeys(listName);

        if (listVisibility.equals("public")) {
            publicListRadiobutton.click();
        } else if (listVisibility.equals("private")){
            privateListRadiobutton.click();
        }
        createButton.click();
    }

    public List<String> getListsCreatedByUser() {
        List<String> listsNames = new ArrayList<>();

        for (WebElement list : listsCreatedByUser) {
            listsNames.add(list.getText());
        }
        return listsNames;
    }

    public List<String> getItemsInList(String listName) {

        find(By.xpath("//a[contains(text(),'" + listName + "')]")).click();

        List<String> itemsTitles = new ArrayList<>();

        for (WebElement item : itemsInList) {

            itemsTitles.add(item.getText().substring(3));
        }

        return itemsTitles;
    }

    public String searchInPublicLists(String listName) {
        publicListsSearchbar.sendKeys(listName);
        publicListsSearchButton.click();
        return "ceva";
    }

    public List<String> getListsMenuOptions() {
        List<String> menuOptions = new ArrayList<>();

        for (WebElement option : listsMenuOptions) {
            System.out.println(option.getText());
        }

        return menuOptions;
    }

}

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

public class ListsPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.ID, using = "list_new_title")
    private WebElement listTitleInput;
    @FindBy(how = How.ID, using = "list-new-create")
    private WebElement createButton;
    @FindBy(how = How.XPATH, using = "//h4//a")
    private List<WebElement> listsCreatedByUser;
    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Publică')]")
    private WebElement publicListRadiobutton;
    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Privată')]")
    private WebElement privateListRadiobutton;
    @FindBy(how = How.ID, using = "add-list")
    private WebElement createNewListButton;

    public ListsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void createList(String listName, String listVisibility) {
        createNewListButton.click();
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

}

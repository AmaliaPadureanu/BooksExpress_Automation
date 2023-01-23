package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.GenericUtils;
import utils.WaitUtils;

import java.time.Duration;

public class ContactPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.ID, using = "subject")
    private WebElement subjectInput;
    @FindBy(how = How.ID, using = "another_subject")
    private WebElement anotherSubjectInput;
    @FindBy(how = How.ID, using = "message")
    private WebElement messageInput;
    @FindBy(how = How.ID, using = "name")
    private WebElement nameInput;
    @FindBy(how = How.ID, using = "email")
    private WebElement emailInput;
    @FindBy(how = How.ID, using = "order_number")
    private WebElement orderNoInput;
    @FindBy(how = How.ID, using = "send-message")
    private WebElement sendButton;
    @FindBy(how = How.ID, using = "success")
    private WebElement confirmationMessage;

    public ContactPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getConfirmationMessage() {
        WaitUtils.waitForVisibilityOf(driver, confirmationMessage, 10);
        return confirmationMessage.getText();
    }

    public void contactAsUnregisteredUser(String anotherSubject, String message, String name, String email, String orderNo) {
        Select selectSubject = new Select(subjectInput);
        selectSubject.selectByIndex(GenericUtils.getRandomNumber(1, 4));
        WebElement selectedOption = selectSubject.getFirstSelectedOption();

        if (selectedOption.getText().contains("Altul")) {
            anotherSubjectInput.sendKeys(anotherSubject);
        }
        messageInput.sendKeys(message);
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        orderNoInput.sendKeys(orderNo);
        sendButton.click();
    }

    public void contactAsRegisteredUser(String anotherSubject, String message, String orderNo) {
        Select selectSubject = new Select(subjectInput);
        selectSubject.selectByIndex(GenericUtils.getRandomNumber(1, 4));
        WebElement selectedOption = selectSubject.getFirstSelectedOption();

        if (selectedOption.getText().contains("Altul")) {
            anotherSubjectInput.sendKeys(anotherSubject);
        }
        messageInput.sendKeys(message);
        orderNoInput.sendKeys(orderNo);
        sendButton.click();
    }
}

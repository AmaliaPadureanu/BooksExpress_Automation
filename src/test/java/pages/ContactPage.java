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
    @FindBy(how = How.CSS, using = "div[data-for='subject']")
    private WebElement subjectError;
    @FindBy(how = How.CSS, using = "div[data-for='another_subject']")
    private WebElement anotherSubjectError;
    @FindBy(how = How.CSS, using = "div[data-for='message']")
    private WebElement messageError;
    @FindBy(how = How.CSS, using = "div[data-for='name']")
    private WebElement nameError;
    @FindBy(how = How.CSS, using = "div[data-for='email']")
    private WebElement emailError;

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

    public void selectSubject(int subjectIndex) {
        Select selectSubject = new Select(subjectInput);
        selectSubject.selectByIndex(subjectIndex);
    }

    public void fillInContactForm(String message, String name, String email, String orderNo) {
        messageInput.sendKeys(message);
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        orderNoInput.sendKeys(orderNo);
        sendButton.click();
    }

    public boolean checkError(String expectedError, String errorType) {
        switch (errorType)  {
            case "subjectError" : {
                return isErrorMessageEqualToExpected(expectedError, subjectError);
            }
            case "anotherSubjectError" : {
                return isErrorMessageEqualToExpected(expectedError, anotherSubjectError);
            }
            case "messageError" : {
                return isErrorMessageEqualToExpected(expectedError, messageError);
            }
            case "nameError" : {
                return isErrorMessageEqualToExpected(expectedError, nameError);
            }
            case "emailError" : {
                return isErrorMessageEqualToExpected(expectedError, emailError);
            }
            default: return false;
        }
    }

    private boolean isErrorMessageEqualToExpected(String expectedError, WebElement element) {
        if (expectedError.length() > 0) {
            wait.until(ExpectedConditions.visibilityOf(element));
            return expectedError.equalsIgnoreCase(element.getText());
        }
        return true;
    }
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;
import java.time.Duration;
import java.util.List;

public class LoginPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.ID, using = "show-user")
    private WebElement accountButton;
    @FindBy(how = How.ID, using = "username")
    private WebElement emailInput;
    @FindBy(how = How.ID, using = "email-button")
    private WebElement continueButton;
    @FindBy(how = How.ID, using = "password")
    private WebElement passwordInput;
    @FindBy(how = How.CSS, using = "#user-data > ul > li:nth-child(8) > a")
    private WebElement logoutButton;
    @FindBy(how = How.ID, using = "login-button")
    private WebElement loginButton;
    @FindBy(how = How.LINK_TEXT, using = "Info")
    private WebElement info;
    @FindBy(how = How.CSS, using = "#user-data")
    private List<WebElement> accountOptions;
    @FindBy(how = How.ID, using = "reset-password")
    private WebElement resetPasswordButton;
    @FindBy(how = How.ID, using = "reset-message")
    private WebElement resetPasswordMessage;
    @FindBy(how = How.ID, using = "login-message")
    private WebElement wrongPasswordError;
    @FindBy(how = How.ID, using = "password-message")
    private WebElement emptyPasswordError;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void logInWith(String email, String password) {
        emailInput.clear();
        emailInput.sendKeys(email);
        continueButton.click();
        WaitUtils.waitForElementToBeClickable(driver, passwordInput, 10);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
        continueButton.click();
    }

    public int logout() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(accountButton).perform();
        logoutButton.click();
        return accountOptions.size();
    }

    public boolean getInfo() {
        Actions actions = new Actions(driver);
        actions.moveToElement(accountButton).perform();

        if (info.isDisplayed()) {
            return true;
        }
        return false;
    }

    public String resetPassword(String email) {
        emailInput.sendKeys(email);
        continueButton.click();
        WaitUtils.waitForVisibilityOf(driver, resetPasswordButton, 10);
        resetPasswordButton.click();
        WaitUtils.waitForVisibilityOf(getDriver(), resetPasswordMessage, 5);
        return resetPasswordMessage.getText();
    }

    public boolean checkPasswordError(String expectedError, String errorType) {
        switch (errorType) {
            case "wrongPasswordError": {
                return isErrorMessageEqualToExpected(expectedError, wrongPasswordError);
            }
            case "emptyPasswordError": {
                return isErrorMessageEqualToExpected(expectedError, emptyPasswordError);
            }
            default: return false;
        }
    }

    private boolean isErrorMessageEqualToExpected(String expectedError, WebElement element) {
       wait.until(ExpectedConditions.visibilityOf(element));
       return expectedError.equalsIgnoreCase(element.getText());

    }
}

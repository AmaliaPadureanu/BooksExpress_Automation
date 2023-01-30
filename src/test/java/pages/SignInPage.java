package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;
import java.time.Duration;

public class SignInPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.ID, using = "email")
    private WebElement emailInput;
    @FindBy(how = How.ID, using = "first_name")
    private WebElement firstnameInput;
    @FindBy(how = How.ID, using = "last_name")
    private WebElement lastnameInput;
    @FindBy(how = How.ID, using = "password")
    private WebElement passwordInput;
    @FindBy(how = How.ID, using = "confirm")
    private WebElement confirmPasswordInput;
    @FindBy(how = How.ID, using = "submit")
    private WebElement createAccountButton;
    @FindBy(how = How.ID, using = "register-failed")
    private WebElement registerFailedError;
    @FindBy(how = How.CSS, using = "div[data-for='first_name']")
    private WebElement firstNameError;
    @FindBy(how = How.CSS, using = "div[data-for='last_name']")
    private WebElement lastNameError;
    @FindBy(how = How.CSS, using = "div[data-for='password']")
    private WebElement passwordError;
    @FindBy(how = How.CSS, using = "div[data-for='confirm']")
    private WebElement confirmPasswordError;
    @FindBy(how = How.CSS, using = "a[title='Books Express este o librarie online cu carti din toata lumea']")
    private WebElement logo;

    public SignInPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void signInWith(String email, String firstname, String lastname, String password, String confirmPassword) {
        emailInput.clear();
        emailInput.sendKeys(email);
        firstnameInput.sendKeys(firstname);
        lastnameInput.sendKeys(lastname);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(confirmPassword);
        createAccountButton.click();
    }

    public String getRegisterFailedError() {
        WaitUtils.waitForVisibilityOf(driver, registerFailedError, 10);
        return registerFailedError.getText();
    }

    public boolean checkError(String expectedError, String errorType) {
        switch (errorType)  {
            case "emailError" : {
                return isErrorMessageEqualToExpected(expectedError, registerFailedError);
            }
            case "firstNameError" : {
                return isErrorMessageEqualToExpected(expectedError, firstNameError);
            }
            case "lastNameError" : {
                return isErrorMessageEqualToExpected(expectedError, lastNameError);
            }
            case "passwordError" : {
                return isErrorMessageEqualToExpected(expectedError, passwordError);
            }
            case "confirmPasswordError" : {
                return isErrorMessageEqualToExpected(expectedError, confirmPasswordError);
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

    public void goBackToHomePage() {
        logo.click();
    }
}

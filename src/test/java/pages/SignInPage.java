package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignInPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//span[normalize-space()='Cont']")
    private WebElement accountButton;
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Cont Nou']")
    private WebElement newAccountButton;
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

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(accountButton).click(newAccountButton).perform();
    }

    public void signInWith(String email, String firstname, String lastname, String password) {
        emailInput.sendKeys(email);
        firstnameInput.sendKeys(firstname);
        lastnameInput.sendKeys(lastname);
        passwordInput.sendKeys(password);
        passwordInput.sendKeys(password);
        createAccountButton.click();
    }
}

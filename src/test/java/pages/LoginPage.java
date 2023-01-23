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

public class LoginPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.ID, using = "show-user")
    private WebElement accountButton;
    @FindBy(how = How.CSS, using = "#user-data > ul > li:nth-child(1) > a")
    private WebElement enterAccountButton;
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
    private By ACCOUNT_OPTIONS = By.xpath("(//ul[@class='jq-dropdown-menu'])[3] //a");
    private By RESET_PASSWORD_BTN = By.id("reset-password");
    private By RESET_PASSWORD_MESSAGE = By.id("reset-message");

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        Actions actions = new Actions(driver);
        actions.moveToElement(accountButton).moveToElement(enterAccountButton).click(enterAccountButton).build().perform();
    }

    public void logInWith(String email, String password) {
        emailInput.sendKeys(email);
        continueButton.click();
        WaitUtils.waitForElementToBeClickable(driver, passwordInput, 10);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public int logout() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(accountButton).perform();
        logoutButton.click();
        return findAll(ACCOUNT_OPTIONS).size();
    }

    public boolean getInfo() {
        Actions actions = new Actions(driver);
        actions.moveToElement(accountButton).perform();

        if (info.isDisplayed()) {
            return true;
        }
        return false;
    }

//    public String resetPassword(String email) {
//        type(EMAIL_FIELD, email);
//        find(CONTINUA_BTN).click();
//        WaitUtils.waitForVisibilityOf(getDriver(), RESET_PASSWORD_BTN, 5);
//        click(RESET_PASSWORD_BTN);
//        WaitUtils.waitForVisibilityOf(getDriver(), RESET_PASSWORD_MESSAGE, 5);
//        return getText(RESET_PASSWORD_MESSAGE);
//    }
}

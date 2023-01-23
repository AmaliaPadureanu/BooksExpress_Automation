package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utils.WaitUtils;

public class LoginPage extends BasePage {

    private By ACCOUNT_BTN = By.xpath("//a[@id='show-user']//i[@class='fa fa-angle-down']");
    private By ENTER_ACCOUNT_LINK = By.xpath("//a[contains(text(),'Intră în cont')]");
    private By EMAIL_FIELD = By.id("username");
    private By CONTINUA_BTN = By.id("email-button");
    private By PASSWORD_FIELD = By.id("password");
    private By LOGOUT_BTN = By.xpath("//a[normalize-space()='Log out']");
    private By ACCOUNT_OPTIONS = By.xpath("(//ul[@class='jq-dropdown-menu'])[3] //a");
    private By RESET_PASSWORD_BTN = By.id("reset-password");
    private By RESET_PASSWORD_MESSAGE = By.id("reset-message");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(find(ACCOUNT_BTN)).click(find(ENTER_ACCOUNT_LINK)).perform();
    }

    public void logInWith(String email, String password) {
        type(EMAIL_FIELD, email);
        find(CONTINUA_BTN).click();
        type(PASSWORD_FIELD, password);
        find(ENTER_ACCOUNT_LINK).click();
    }

    public int logout() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(find(ACCOUNT_BTN)).perform();
        find(LOGOUT_BTN).click();
        return findAll(ACCOUNT_OPTIONS).size();
    }

    public String resetPassword(String email) {
        type(EMAIL_FIELD, email);
        find(CONTINUA_BTN).click();
        WaitUtils.waitForVisibilityOf(getDriver(), RESET_PASSWORD_BTN, 5);
        click(RESET_PASSWORD_BTN);
        WaitUtils.waitForVisibilityOf(getDriver(), RESET_PASSWORD_MESSAGE, 5);
        return getText(RESET_PASSWORD_MESSAGE);
    }
}

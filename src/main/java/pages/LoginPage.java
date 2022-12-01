package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class LoginPage extends BasePage {

    private By ACCOUNT_BTN = By.xpath("//a[@id='show-user']//i[@class='fa fa-angle-down']");

    private By CONT_LINK = By.xpath("//a[@id='show-user']//i[@class='fa fa-angle-down']");
    private By INTRA_IN_CONT_LINK = By.xpath("//a[contains(text(),'Intră în cont')]");
    private By EMAIL_FIELD = By.id("username");
    private By CONTINUA_BTN = By.id("email-button");
    private By PASSWORD_FIELD = By.id("password");
    private By LOGOUT_BTN = By.xpath("//a[normalize-space()='Log out']");

    private By ACCOUNT_OPTIONS = By.xpath("(//ul[@class='jq-dropdown-menu'])[3] //a");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(find(ACCOUNT_BTN)).click(find(INTRA_IN_CONT_LINK)).perform();
    }

    public void logInWith(String email, String password) {
        type(EMAIL_FIELD, email);
        find(CONTINUA_BTN).click();
        type(PASSWORD_FIELD, password);
        find(INTRA_IN_CONT_LINK).click();
    }

    public int logout() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(find(ACCOUNT_BTN)).perform();
        find(LOGOUT_BTN).click();
        return findAll(ACCOUNT_OPTIONS).size();
    }
}

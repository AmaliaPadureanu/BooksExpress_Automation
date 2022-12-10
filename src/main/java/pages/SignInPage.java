package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class SignInPage extends BasePage {

    private By ACCOUNT_LINK = By.xpath("//span[normalize-space()='Cont']");
    private By NEW_ACCOUNT_LINK = By.xpath("//a[normalize-space()='Cont Nou']");
    private By EMAIL_FIELD = By.id("email");
    private By FIRSTNAME_FIELD = By.id("first_name");
    private By LASTNAME_FIELD = By.id("last_name");
    private By PASSWORD_FIELD = By.id("password");
    private By CONFIRM_PASSWORD_FIELD = By.id("confirm");
    private By CREATE_ACCOUNT_BTN = By.id("submit");

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(find(ACCOUNT_LINK)).click(find(NEW_ACCOUNT_LINK)).perform();
    }

    public void signInWith(String email, String firstname, String lastname, String password) {
        type(EMAIL_FIELD, email);
        type(FIRSTNAME_FIELD, firstname);
        type(LASTNAME_FIELD, lastname);
        type(PASSWORD_FIELD, password);
        type(CONFIRM_PASSWORD_FIELD, password);
        click(CREATE_ACCOUNT_BTN);
    }
}

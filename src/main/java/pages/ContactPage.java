package pages;

import base.BasePage;
import utils.JavaScriptUtils;
import utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ContactPage extends BasePage {

    By SUBJECT = By.id("subject");
    By MESSAGE = By.id("message");
    By NAME = By.id("name");
    By EMAIL = By.id("email");
    By ORDER_NO = By.id("order_number");

    By ANOTHER_SUBJECT = By.id("another_subject");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public void sendContactFormUnregisteredUser(String subject, String anotherSubject, String message, String name, String email, String orderNo) {
        Select select = new Select(find(SUBJECT));
        select.selectByVisibleText(subject);
        if (find(ANOTHER_SUBJECT).isDisplayed()) {
            type(ANOTHER_SUBJECT, anotherSubject);
        }
        type(MESSAGE, message);
        type(NAME, name);
        type(EMAIL, email);
        type(ORDER_NO, orderNo);
        WebElement sendBtn = WaitUtils.waitForElementToBeClickable(getDriver(), By.id("send-message"), 5);
        JavaScriptUtils.click(getDriver(), sendBtn);
    }

    public void sendContactFormRegisteredUser(String subject, String anotherSubject, String message, String orderNo) {
        Select select = new Select(find(SUBJECT));
        select.selectByVisibleText(subject);
        if (find(ANOTHER_SUBJECT).isDisplayed()) {
            type(ANOTHER_SUBJECT, anotherSubject);
        }
        type(MESSAGE, message);
        type(ORDER_NO, orderNo);
        WebElement sendBtn = WaitUtils.waitForElementToBeClickable(getDriver(), By.id("send-message"), 5);
        JavaScriptUtils.click(getDriver(), sendBtn);
    }
}

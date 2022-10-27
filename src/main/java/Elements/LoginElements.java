package Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginElements {

    WebDriver driver;

    @FindBy(xpath = "//span[normalize-space()='Cont']") public WebElement CONT_LINK;
    @FindBy(xpath = "//a[contains(text(),'Intră în cont')]") public WebElement INTR_IN_CONT_LINK;
    @FindBy(xpath = "//input[@id='username']") public WebElement EMAIL_FIELD;
    @FindBy(xpath = "//a[@id='email-button']") public WebElement CONTINUA_BUTTON;
    @FindBy(xpath = "//input[@id='password']") public WebElement PASSWORD_FIELD;

    public LoginElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}

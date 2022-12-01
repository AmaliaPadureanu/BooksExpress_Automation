package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {


    By accountBtn = By.xpath("//a[@id='show-user']//i[@class='fa fa-angle-down']");

    private String CONT_LINK = "//a[@id='show-user']//i[@class='fa fa-angle-down']";
    private String INTRA_IN_CONT_LINK = "//a[contains(text(),'Intră în cont')]";
    private String EMAIL_FIELD = "//input[@id='username']";
    private String CONTINUA_BTN = "//a[@id='email-button']";
    private String PASSWORD_FIELD = "//input[@id='password']";
    private String LOGOUT_BTN = "//a[normalize-space()='Log out']";

    //WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver().findElement(accountBtn)).click(getDriver().findElement(By.xpath(INTRA_IN_CONT_LINK))).perform();
    }

    public void logInWith(String email, String password) {
        WebElement emailField = getDriver().findElement(By.xpath(EMAIL_FIELD));
        emailField.sendKeys(email);
        getDriver().findElement(By.xpath(CONTINUA_BTN)).click();
        getDriver().findElement(By.xpath(PASSWORD_FIELD)).sendKeys(password);
        getDriver().findElement(By.xpath(INTRA_IN_CONT_LINK)).click();
    }

    public int logout() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver().findElement(accountBtn)).perform();
        getDriver().findElement(By.xpath(LOGOUT_BTN)).click();
        int optionsAvailableAfterLogout = getDriver().findElements(By.xpath("(//ul[@class='jq-dropdown-menu'])[3] //a")).size();
        return optionsAvailableAfterLogout;
    }

//    @Override
//    public String getPageTitle() {
//        return driver.getTitle();
//    }
//
//    @Override
//    public String getPageURL() {
//        return driver.getCurrentUrl();
//    }
}

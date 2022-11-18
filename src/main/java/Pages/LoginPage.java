package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private String CONT_LINK = "//a[@id='show-user']//i[@class='fa fa-angle-down']";
    private String INTRA_IN_CONT_LINK = "//a[contains(text(),'Intră în cont')]";
    private String EMAIL_FIELD = "//input[@id='username']";
    private String CONTINUA_BTN = "//a[@id='email-button']";
    private String PASSWORD_FIELD = "//input[@id='password']";
    private String LOGOUT_BTN = "//a[normalize-space()='Log out']";

    public void open() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(CONT_LINK))).perform();
        driver.findElement(By.xpath(INTRA_IN_CONT_LINK)).click();
    }

    public void logInWith(String email, String password) {
        WebElement emailField = driver.findElement(By.xpath(EMAIL_FIELD));
        emailField.sendKeys(email);

        driver.findElement(By.xpath(CONTINUA_BTN)).click();
        WebDriverWait wait = new WebDriverWait(driver, 6);
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PASSWORD_FIELD)));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        passwordField.sendKeys(password);

        driver.findElement(By.xpath(INTRA_IN_CONT_LINK)).click();
    }

    public int logout() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(CONT_LINK))).perform();
        driver.findElement(By.xpath(LOGOUT_BTN)).click();
        int optionsAvailableAfterLogout = driver.findElements(By.xpath("(//ul[@class='jq-dropdown-menu'])[3] //a")).size();
        return optionsAvailableAfterLogout;
    }


}

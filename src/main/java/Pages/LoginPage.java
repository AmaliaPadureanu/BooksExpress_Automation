package Pages;

import Elements.LoginElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    public String CONT_LINK = "//a[@id='show-user']//i[@class='fa fa-angle-down']";
    public String INTRA_IN_CONT_LINK = "//a[contains(text(),'Intră în cont')]";
    public String EMAIL_FIELD = "//input[@id='username']";
    public String CONTINUA_BTN = "//a[@id='email-button']";
    public String PASSWORD_FIELD = "//input[@id='password']";
    private final String URL = "login";
    public String LOGOUT_BTN = "//a[normalize-space()='Log out']";


    public void login() {
        driver.findElement(By.xpath(CONT_LINK)).click();
        driver.findElement(By.xpath(INTRA_IN_CONT_LINK)).click();
    }

    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL);
    }


    public void logInWith(String email, String password) throws InterruptedException {
        WebElement emailField = driver.findElement(By.xpath(EMAIL_FIELD));
        emailField.sendKeys(email);

        driver.findElement(By.xpath(CONTINUA_BTN)).click();
        WebDriverWait wait = new WebDriverWait(driver, 6);
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PASSWORD_FIELD)));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        passwordField.sendKeys(password);

        driver.findElement(By.xpath(INTRA_IN_CONT_LINK)).click();
    }


}

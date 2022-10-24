package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    public String CONT_LINK = "//span[normalize-space()='Cont']";
    public String INTRA_IN_CONT_LINK = "//a[contains(text(),'Intră în cont')]";
    public String EMAIL_FIELD = "//input[@id='username']";
    public String CONTINUA_BTN = "//a[@id='email-button']";
    public String PASSWORD_FIELD = "//input[@id='password']";
    private final String URL = "login";


    public void open() {
        driver.findElement(By.xpath(CONT_LINK)).click();
        driver.findElement(By.xpath(INTRA_IN_CONT_LINK)).click();
        Set<String> handles = driver.getWindowHandles();

        String parentHandle = driver.getWindowHandle();

        for (String handle : handles) {
            if (handle != parentHandle) {
                driver.switchTo().window(handle);
            }
        }
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
        Thread.sleep(4000);
    }

}

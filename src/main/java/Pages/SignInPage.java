package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class SignInPage {

    public WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    private String CONT_LINK = "//span[normalize-space()='Cont']";
    private String CONT_NOU_LINK = "//a[normalize-space()='Cont Nou']";
    private String EMAIL_FIELD = "//input[@id='email']";
    private String PRENUME_FIELD = "//input[@id='first_name']";
    private String NUME_FIELD = "//input[@id='last_name']";
    private String PAROLA_FIELD = "//input[@id='password']";
    private String CONFIRMA_PAROLA_FIELD = "//input[@id='confirm']";
    private String CREAZA_CONTUL_BTN = "//a[@id='submit']";
    private final String URL = "register";

    public void open() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(CONT_LINK))).perform();
        driver.findElement(By.xpath(CONT_NOU_LINK)).click();
    }

    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL);
    }

    public void signInWith(String email, String firstname, String lastname, String password) {
        driver.findElement(By.xpath(EMAIL_FIELD)).sendKeys(email);
        driver.findElement(By.xpath(PRENUME_FIELD)).sendKeys(firstname);
        driver.findElement(By.xpath(NUME_FIELD)).sendKeys(lastname);
        driver.findElement(By.xpath(PAROLA_FIELD)).sendKeys(password);
        driver.findElement(By.xpath(CONFIRMA_PAROLA_FIELD)).sendKeys(password);
        driver.findElement(By.xpath(CREAZA_CONTUL_BTN)).click();
    }
}

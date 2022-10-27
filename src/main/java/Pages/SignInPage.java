package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage {
    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    public String CONT_LINK = "//span[normalize-space()='Cont']";
    public String CONT_NOU_LINK = "//a[normalize-space()='Cont Nou']";
    public String EMAIL_FIELD = "//input[@id='email']";
    public String PRENUME_FIELD = "//input[@id='first_name']";
    public String NUME_FIELD = "//input[@id='last_name']";
    public String PAROLA_FIELD = "//input[@id='password']";
    public String CONFIRMA_PAROLA_FIELD = "//input[@id='confirm']";
    public String CREAZA_CONTUL_BTN = "//a[@id='submit']";
    private final String URL = "register";

    public void open() {
        driver.findElement(By.xpath(CONT_LINK)).click();
        driver.findElement(By.xpath(CONT_NOU_LINK)).click();
    }

    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL);
    }


    public void signInWith(String email, String prenume, String nume, String parola) throws InterruptedException {
        WebElement emailField = driver.findElement(By.xpath(EMAIL_FIELD));
        emailField.sendKeys(email);

        WebElement prenumeField = driver.findElement(By.xpath(PRENUME_FIELD));
        prenumeField.sendKeys(prenume);

        WebElement numeField = driver.findElement(By.xpath(NUME_FIELD));
        numeField.sendKeys(nume);

        WebElement parolaField = driver.findElement(By.xpath(PAROLA_FIELD));
        parolaField.sendKeys(parola);

        WebElement confirmaParolaField = driver.findElement(By.xpath(CONFIRMA_PAROLA_FIELD));
        confirmaParolaField.sendKeys(parola);

        driver.findElement(By.xpath(CREAZA_CONTUL_BTN)).click();
        Thread.sleep(4000);
    }
}

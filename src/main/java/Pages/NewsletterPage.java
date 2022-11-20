package Pages;

import Utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NewsletterPage {

    WebDriver driver;

    public NewsletterPage(WebDriver driver) {
        this.driver = driver;
    }

    private String EMAIL_FIELD = "//input[@id='email']";
    private String NUME_FIELD = "//input[@id='name']";
    private String LIMBILE_PREFERATE_FIELD = "//ul[@class='select2-selection__rendered']";
    private String ABONARE_CHECKBOX = "//label[@for='one_chapter']";
    private String MA_ABONEZ_BTN = "//button[@class='button big primary save']";

    public void subscribeToNewsletter(String email, String nume, int language) {
        driver.findElement(By.xpath(EMAIL_FIELD)).sendKeys(email);
        driver.findElement(By.xpath(NUME_FIELD)).sendKeys(nume);
        driver.findElement(By.xpath(LIMBILE_PREFERATE_FIELD)).click();
        List<WebElement> options = driver.findElements(By.xpath("//li[contains(@class,'select2-results__option')]"));
        options.get(language).click();
        driver.findElement(By.xpath(ABONARE_CHECKBOX)).click();
        driver.findElement(By.xpath(ABONARE_CHECKBOX)).click();
        driver.findElement(By.xpath(MA_ABONEZ_BTN)).click();
    }

}

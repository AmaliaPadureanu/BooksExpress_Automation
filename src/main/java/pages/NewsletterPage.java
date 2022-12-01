package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NewsletterPage extends BasePage {

    //WebDriver driver;

    public NewsletterPage(WebDriver driver) {
        super(driver);
    }

    private By NEWSLETTER_LINK = By.xpath("//a[contains(text(),'Abonează-te la newsletter!')]");
    private By EMAIL_FIELD = By.id("email");
    private By NUME_FIELD = By.id("name");
    private By PREFERRED_LANGUAGES_FIELD = By.xpath("//ul[@class='select2-selection__rendered']");
    private By SUBSCRIBE_CHECKBOX = By.xpath("//label[@for='one_chapter']");
    private By SUBSCRIBE_TO_ONE_CHAPTER_PER_DAY_BRN = By.xpath("//button[@class='button big primary save']");

    public void subscribeToNewsletter(String email, String nume, int language) {
        click(NEWSLETTER_LINK);
        type(EMAIL_FIELD, email);
        //driver.findElement(By.xpath(EMAIL_FIELD)).sendKeys(email);
        //driver.findElement(By.xpath(NUME_FIELD)).sendKeys(nume);
        type(NUME_FIELD, nume);
//        driver.findElement(By.xpath(LIMBILE_PREFERATE_FIELD)).click();
        click(PREFERRED_LANGUAGES_FIELD);
//        List<WebElement> options = driver.findElements(By.xpath("//li[contains(@class,'select2-results__option')]"));
        List<WebElement> options = findAll(By.xpath("//li[contains(@class,'select2-results__option')]"));
//        options.get(language).click();
        options.get(language).click();
//        driver.findElement(By.xpath(ABONARE_CHECKBOX)).click();
        click(SUBSCRIBE_CHECKBOX);
//        driver.findElement(By.xpath(ABONARE_CHECKBOX)).click();
        click(SUBSCRIBE_TO_ONE_CHAPTER_PER_DAY_BRN);
//        driver.findElement(By.xpath(MA_ABONEZ_BTN)).click();
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

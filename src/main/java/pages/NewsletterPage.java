package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class NewsletterPage extends BasePage {

    private By NEWSLETTER_LINK = By.xpath("//a[contains(text(),'Abonează-te la newsletter!')]");
    private By EMAIL_FIELD = By.id("email");
    private By NAME_FIELD = By.id("name");
    private By PREFERRED_LANGUAGES_FIELD = By.xpath("//ul[@class='select2-selection__rendered']");
    private By LANGUAGE_OPTIONS = By.xpath("//li[contains(@class,'select2-results__option')]");
    private By SUBSCRIBE_CHECKBOX = By.xpath("//label[@for='one_chapter']");
    private By SUBSCRIBE_TO_ONE_CHAPTER_PER_DAY_BTN = By.xpath("//button[@class='button big primary save']");

    public NewsletterPage(WebDriver driver) {
        super(driver);
    }

    public void subscribeToNewsletter(String email, String nume, int language) {
        click(NEWSLETTER_LINK);
        type(EMAIL_FIELD, email);
        type(NAME_FIELD, nume);
        click(PREFERRED_LANGUAGES_FIELD);
        List<WebElement> options = findAll(LANGUAGE_OPTIONS);
        options.get(language).click();
        click(SUBSCRIBE_CHECKBOX);
        click(SUBSCRIBE_TO_ONE_CHAPTER_PER_DAY_BTN);
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.GenericUtils;

import java.time.Duration;
import java.util.List;

public class NewsletterPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Abonează-te la newsletter!')]")
    private WebElement newsletterButton;
    @FindBy(how = How.ID, using = "email")
    private WebElement emailInput;
    @FindBy(how = How.ID, using = "name")
    private WebElement nameInput;
    @FindBy(how = How.XPATH, using = "//ul[@class='select2-selection__rendered']")
    private WebElement languagesField;
    @FindBy(how = How.XPATH, using = "//li[contains(@class,'select2-results__option')]")
    private List<WebElement> languageOptions;
    @FindBy(how = How.XPATH, using = "//label[@for='one_chapter']")
    private WebElement subscribeTo0neChapterADayCheckbox;
    @FindBy(how = How.XPATH, using = "//button[@class='button big primary save']")
    private WebElement subscribeButton;
    @FindBy(how = How.CSS, using = "#result > p")
    private WebElement afterSubscribeMessage;

    public NewsletterPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void subscribeToNewsletter(String email, String name, boolean subscribeToOneChapterADay) {
        newsletterButton.click();
        emailInput.sendKeys(email);
        nameInput.sendKeys(name);
        languagesField.click();
        languageOptions.get(GenericUtils.getRandomNumber(0, 6)).click();

        if (subscribeToOneChapterADay) {
            subscribeTo0neChapterADayCheckbox.click();
        }
        subscribeButton.click();
    }

    public String getAfterSubscribeMessage() {
        return afterSubscribeMessage.getText();
    }
}

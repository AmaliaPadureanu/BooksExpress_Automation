package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserDetailsPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//div[@id='profile']//button[@class='button primary edit'][contains(text(),'Modifică')]")
    private WebElement modifyButton;
    @FindBy(how = How.XPATH, using = "//button[@class='button primary save'][contains(.,'Actualizează')]")
    private WebElement updateButton;
    @FindBy(how = How.XPATH, using = "//label[@for='gender_F']")
    private WebElement msTitleRadiobutton;
    @FindBy(how = How.XPATH, using = "//label[@for='gender_M']")
    private WebElement mrTitleRadiobutton;
    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Dl.')]")
    private WebElement mrTitle;
    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Dna.')]")
    private WebElement msTitle;
    @FindBy(how = How.ID, using = "first_name")
    private WebElement firstnameInput;
    @FindBy(how = How.ID, using = "last_name")
    private WebElement lastnameInput;
    @FindBy(how = How.CSS, using = "div[id='profile'] div:nth-child(4)")
    private WebElement name;
    @FindBy(how = How.CSS, using = "div[id='main'] div:nth-child(6)")
    private WebElement phoneNumber;
    @FindBy(how = How.ID, using = "phone")
    private WebElement phoneNumberInput;
    @FindBy(how = How.CSS, using = "div[id='main'] div:nth-child(8)")
    private WebElement emailInput;
    @FindBy(how = How.ID, using = "email")
    private WebElement emailIn;

    public UserDetailsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String editUserTitle(Character gender) {
        modifyButton.click();

        if (gender.equals('M')) {
            mrTitleRadiobutton.click();
            updateButton.click();
            return mrTitle.getText();
        }
        else if (gender.equals('F')) {
            msTitleRadiobutton.click();
            updateButton.click();
            return msTitle.getText();
        }
        else {
            System.out.println("Value is not valid. Please choose between M and F");
        }

        return null;
    }

    public String editFirstName(String firstName) {
        modifyButton.click();
        firstnameInput.clear();
        firstnameInput.sendKeys(firstName);
        updateButton.click();
        return name.getText();
    }

    public String editLastName(String lastName) {
        modifyButton.click();
        lastnameInput.clear();
        lastnameInput.sendKeys(lastName);
        updateButton.click();
        return name.getText();
    }

    public String editPhoneNumber(String phoneNr) {
        modifyButton.click();
        phoneNumberInput.clear();
        phoneNumberInput.sendKeys(phoneNr);
        updateButton.click();
        return phoneNumber.getText();
    }

    public String editEmail(String email) {
        modifyButton.click();
        emailIn.clear();
        emailIn.sendKeys(email);
        updateButton.click();
        return emailInput.getText();
    }
}
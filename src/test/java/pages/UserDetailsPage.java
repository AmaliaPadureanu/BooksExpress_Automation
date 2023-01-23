package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserDetailsPage extends BasePage {

    private By MODIFY_BTN = By.xpath("//div[@id='profile']//button[@class='button primary edit'][contains(text(),'Modifică')]");
    private By UPDATE_BTN = By.xpath("//button[@class='button primary save'][contains(.,'Actualizează')]");
    private By MS_TITLE_RADIOBTN = By.xpath("//label[@for='gender_F']");
    private By MR_TITLE_RADIOBTN = By.xpath("//label[@for='gender_M']");
    private By MR_TITLE = By.xpath("//div[contains(text(),'Dl.')]");
    private By MS_TITLE = By.xpath("//div[contains(text(),'Dna.')]");
    private By FIRST_NAME_FIELD = By.id("first_name");
    private By LAST_NAME_FIELD = By.id("last_name");
    private By NAME = By.cssSelector("div[id='profile'] div:nth-child(4)");
    private By PHONE_NUMBER = By.cssSelector("div[id='main'] div:nth-child(6)");
    private By PHONE_NUMBER_INPUT = By.id("phone");
    private By EMAIL = By.cssSelector("div[id='main'] div:nth-child(8)");
    private By EMAIL_INPUT = By.id("email");

    public UserDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String editUserTitle(Character gender) {
        click(MODIFY_BTN);

        if (gender.equals('M')) {
            click(MR_TITLE_RADIOBTN);
            click(UPDATE_BTN);
            getDriver().navigate().refresh();
            return find(MR_TITLE).getText();
        }
        else if (gender.equals('F')) {
            click(MS_TITLE_RADIOBTN);
            click(UPDATE_BTN);
            getDriver().navigate().refresh();
            return find(MS_TITLE).getText();
        }
        else {
            System.out.println("Value is not valid. Please choose between M and F");
        }

        return null;
    }

    public String editFirstName(String firstName) {
        click(MODIFY_BTN);
        clear(FIRST_NAME_FIELD);
        type(FIRST_NAME_FIELD, firstName);
        click(UPDATE_BTN);
        getDriver().navigate().refresh();
        return find(NAME).getText();
    }

    public String editLastName(String lastName) {
        click(MODIFY_BTN);
        clear(LAST_NAME_FIELD);
        type(LAST_NAME_FIELD, lastName);
        click(UPDATE_BTN);
        getDriver().navigate().refresh();
        return find(NAME).getText();
    }

    public String editPhoneNumber(String phoneNr) {
        click(MODIFY_BTN);
        clear(PHONE_NUMBER_INPUT);
        type(PHONE_NUMBER_INPUT, phoneNr);
        click(UPDATE_BTN);
        getDriver().navigate().refresh();
        return find(PHONE_NUMBER).getText();
    }

    public String editEmail(String email) {
        click(MODIFY_BTN);
        clear(EMAIL_INPUT);
        type(EMAIL_INPUT, email);
        click(UPDATE_BTN);
        getDriver().navigate().refresh();
        return find(EMAIL).getText();
    }

}
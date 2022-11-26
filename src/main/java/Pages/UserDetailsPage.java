package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserDetailsPage {

    WebDriver driver;

    public UserDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    private String MODIFY_BTN = "//div[@id='profile']//button[@class='button primary edit'][contains(text(),'Modifică')]";
    private String UPDATE_BTN = "//button[@class='button primary save'][contains(.,'Actualizează')]";
    private String MS_TITLE_RADIOBTN = "//label[@for='gender_F']";
    private String MR_TITLE_RADIOBTN = "//label[@for='gender_M']";
    private String MR_TITLE = "//div[contains(text(),'Dl.')]";
    private String MS_TITLE = "//div[contains(text(),'Dna.')]";
    private String FIRST_NAME_FIELD = "//input[@id='first_name']";
    private String LAST_NAME_FIELD = "//input[@id='last_name']";
    private String NAME = "div[id='profile'] div:nth-child(4)";
    private String PHONE_NUMBER = "div[id='main'] div:nth-child(6)";
    private String PHONE_NUMBER_INPUT = "//input[@id='phone']";
    private String EMAIL = "div[id='main'] div:nth-child(8)";
    private String EMAIL_INPUT = "//input[@id='email']";

    public String editUserTitle(Character gender) {
        driver.findElement(By.xpath(MODIFY_BTN)).click();

        if (gender.equals('M')) {
            driver.findElement(By.xpath(MR_TITLE_RADIOBTN)).click();
            driver.findElement(By.xpath(UPDATE_BTN)).click();
            driver.navigate().refresh();
            return driver.findElement(By.xpath(MR_TITLE)).getText();
        }
        else if (gender.equals('F')) {
            driver.findElement(By.xpath(MS_TITLE_RADIOBTN)).click();
            driver.findElement(By.xpath(UPDATE_BTN)).click();
            driver.navigate().refresh();
            return driver.findElement(By.xpath(MS_TITLE)).getText();
        }
        else {
            System.out.println("Value is not valid. Please choose between M and F");
        }

        return null;
    }

    public String editFirstName(String firstName) {
        driver.findElement(By.xpath(MODIFY_BTN)).click();
        WebElement inputFirstName = driver.findElement(By.xpath(FIRST_NAME_FIELD));
        inputFirstName.clear();
        inputFirstName.sendKeys(firstName);
        driver.findElement(By.xpath(UPDATE_BTN)).click();
        driver.navigate().refresh();
        String newFirstName = driver.findElement(By.cssSelector(NAME)).getText();
        return newFirstName;
    }

    public String editLastName(String lastName) {
        driver.findElement(By.xpath(MODIFY_BTN)).click();
        WebElement inputLastName = driver.findElement(By.xpath(LAST_NAME_FIELD));
        inputLastName.clear();
        inputLastName.sendKeys(lastName);
        driver.findElement(By.xpath(UPDATE_BTN)).click();
        driver.navigate().refresh();
        String newLastName = driver.findElement(By.cssSelector(NAME)).getText();
        return newLastName;
    }

    public String editPhoneNumber(String phoneNr) {
        driver.findElement(By.xpath(MODIFY_BTN)).click();
        WebElement inputPhoneNumber = driver.findElement(By.xpath(PHONE_NUMBER_INPUT));
        inputPhoneNumber.clear();
        inputPhoneNumber.sendKeys(phoneNr);
        driver.findElement(By.xpath(UPDATE_BTN)).click();
        driver.navigate().refresh();
        String newPhoneNumber = driver.findElement(By.cssSelector(PHONE_NUMBER)).getText();
        return newPhoneNumber;
    }

    public String editEmail(String email) {
        driver.findElement(By.xpath(MODIFY_BTN)).click();
        WebElement inputEmail = driver.findElement(By.xpath(EMAIL_INPUT));
        inputEmail.clear();
        inputEmail.sendKeys(email);
        driver.findElement(By.xpath(UPDATE_BTN)).click();
        driver.navigate().refresh();
        String newEmail = driver.findElement(By.cssSelector(EMAIL)).getText();
        return newEmail;
    }

}

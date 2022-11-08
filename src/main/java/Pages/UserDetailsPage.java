package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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


    public String editUserInfo(Character gender) {
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
}

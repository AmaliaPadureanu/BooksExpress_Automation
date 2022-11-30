package Pages;

import Utils.JavaScriptUtils;
import Utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ContactPage {

    WebDriver driver;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sendContactFormUnregisteredUser(String subject, String message, String name, String email, String orderNo) {
        Select select = new Select(driver.findElement(By.id("subject")));
        select.selectByVisibleText(subject);
        driver.findElement(By.id("message")).sendKeys(message);
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("order_number")).sendKeys(orderNo);
        WebElement sendBtn = WaitUtils.waitForElementToBeClickable(driver, By.id("send-message"), 5);
        JavaScriptUtils.click(driver, sendBtn);
    }

    public void sendContactFormRegisteredUser(String subject, String message, String orderNo) {
        Select select = new Select(driver.findElement(By.id("subject")));
        select.selectByVisibleText(subject);
        driver.findElement(By.id("message")).sendKeys(message);
        driver.findElement(By.id("order_number")).sendKeys(orderNo);
        WebElement sendBtn = WaitUtils.waitForElementToBeClickable(driver, By.id("send-message"), 5);
        JavaScriptUtils.click(driver, sendBtn);
    }

}

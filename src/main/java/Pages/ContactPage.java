package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactPage {

    WebDriver driver;
    public String SUBJECT_DROPDOWN = "//select[@id='subject']";
    public String MESSAGE_FIELD = "//textarea[@id='message']";
    public String ORDER_NO_FIELD = "//input[@id='order_number']";
    public String NAME_FIELD = "//input[@id='name']";
    public String EMAIL_FIELD = "//input[@id='email']";
    public String SEND_BTN = "//a[@id='send-message']";

    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sendContactFormUnregisteredUser(String subject, String message, String name, String email, String orderNo) {
        driver.findElement(By.xpath(SUBJECT_DROPDOWN)).click();
        Select select = new Select(driver.findElement(By.xpath(SUBJECT_DROPDOWN)));
        select.selectByVisibleText(subject);
        driver.findElement(By.xpath(MESSAGE_FIELD)).sendKeys(message);
        driver.findElement(By.xpath(NAME_FIELD)).sendKeys(name);
        driver.findElement(By.xpath(EMAIL_FIELD)).sendKeys(email);
        driver.findElement(By.xpath(ORDER_NO_FIELD)).sendKeys(orderNo);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(SEND_BTN))));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

}

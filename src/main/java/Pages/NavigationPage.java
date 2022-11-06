package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationPage {

    WebDriver driver;

    public NavigationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String REDUCERI_LINK = "//a[@href='/reduceri']";
    public String TOP_VANZARI_LINK = "//a[@href='/top/carti']";
    public String NOUTATI_LINK = "//div[@id='submenu']//a[contains(text(),'Noutăți')]";
    public String CADOURI_LINK = "//a[normalize-space()='Cadouri']";
    public String BLOG_LINK = "//div[@id='submenu']//a[normalize-space()='Blog']";
    public String NEWSLETTER_LINK = "//a[contains(text(),'Abonează-te la newsletter!')]";
    public String CONTACT_LINK = "//a[normalize-space()='Contact']";
    public String PRODUSE_DROPDOWN = "//i[@class='fa fa-angle-right right color-bold']";
    public String BUSINESS_CATEGORY = "//a[text()=' Business']";
    public String ECONOMIE_CATEGORY = "div[id='prod-business'] ul[class='col1 left'] li:nth-child(4) a:nth-child(1)";
    public String CONT_LINK = "//span[normalize-space()='Cont']";
    public String PERSONAL_DETAILS = "//ul[@class='jq-dropdown-menu']//a[normalize-space()='Detalii personale']";

    public String selectProductsCategory() throws InterruptedException {
        Actions actions = new Actions(driver);

        WebElement business = driver.findElement(By.xpath(BUSINESS_CATEGORY));

        actions.moveToElement(business).perform();

        WebElement element = driver.findElement(By.partialLinkText("Economie"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(element));

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);

        Thread.sleep(5000);

//       wait.until(ExpectedConditions.urlToBe("https://www.books-express.ro/carti/business-economie/kc"));

        System.out.println(driver.getTitle().toLowerCase());
        return driver.getTitle().toLowerCase();
    }

    public void reduceri() {
        driver.findElement(By.xpath(REDUCERI_LINK)).click();
    }

    public void topVanzari() {
        driver.findElement(By.xpath(TOP_VANZARI_LINK)).click();
    }

    public void noutati() {
        driver.findElement(By.xpath(NOUTATI_LINK)).click();
    }

    public void cadouri() {
        driver.findElement(By.xpath(CADOURI_LINK)).click();
    }

    public void blog() {
        driver.findElement(By.xpath(BLOG_LINK)).click();
    }

    public NewsletterPage newsletter() {
        driver.findElement(By.xpath(NEWSLETTER_LINK)).click();
        return new NewsletterPage(driver);
    }

    public ContactPage contact() {
        driver.findElement(By.xpath(CONTACT_LINK)).click();
        return new ContactPage(driver);
    }

}

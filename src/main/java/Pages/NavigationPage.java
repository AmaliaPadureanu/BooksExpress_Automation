package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
}

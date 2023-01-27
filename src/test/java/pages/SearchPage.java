package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.GenericUtils;
import utils.WaitUtils;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.ID, using = "search")
    private WebElement searchBar;
    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private WebElement searchButton;
    @FindBy(how = How.ID, using = "products")
    private WebElement productsDropdown;
    @FindBy(how = How.CSS, using = "#products-menu > ul > li > a")
    private List<WebElement> products;
    @FindBy(how = How.XPATH, using = "//div[@id='products-submenus']//div//ul[last()]//li")
    private List<WebElement> productsSubmenus;

    public SearchPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public SearchResultsPage search(String item) {
        searchBar.click();
        searchBar.clear();
        searchBar.sendKeys(item);
        searchButton.click();
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage searchRandomProduct() {
        WebElement productCategory = products.get(GenericUtils.getRandomNumber(0, 11));
        String categoryId = productCategory.getAttribute("data-id").split("-")[1];

        Actions actions = new Actions(driver);
        actions.moveToElement(productsDropdown).moveToElement(productCategory).build().perform();

        List<WebElement>  submenus = productCategory.findElements(By.xpath("//div[@id='products-submenus']//div[contains(@id,'" + categoryId + "')]//ul[last()]//li//a"));
        int submenuSize = submenus.size();
        WebElement chosenSubmenu = submenus.get(GenericUtils.getRandomNumber(0, submenuSize));
        WaitUtils.waitForElementToBeClickable(driver, chosenSubmenu, 10).click();

        return new SearchResultsPage(driver);
    }
}

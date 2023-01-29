package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.List;

public class ShoppingCartPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//a[@class='color-theme-5 cart-remove-item']")
    private WebElement deleteButton;
    @FindBy(how = How.ID, using = "cart-added-text")
    private WebElement cartSuccessMessage;
    @FindBy(how = How.CSS, using = "#cart-items > li")
    private List<WebElement> productsInCart;
    @FindBy(how = How.CSS, using = "ul[id='cart-items'] li div div div p")
    private WebElement quantityExceedsStockError;
    @FindBy(how = How.XPATH, using = "//body[1]/div[7]/div[1]/section[1]/div[5]/div[1]/div[2]/div[1]/div[2]/div[1]")
    private WebElement totalPrice;
    @FindBy(how = How.CSS, using = "body > div:nth-child(10) > div:nth-child(1) > section:nth-child(4) > div:nth-child(6) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2)")
    private WebElement deliveryPrice;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void removeFromCart(WebElement element) {
        deleteButton.click();
        SeleniumUtils.refreshPage(driver);
    }

    public int getNumberOfProductsInCart() {
        return productsInCart.size();
    }

    public List<WebElement> getProductsInCart() {
        return productsInCart;
    }

    public void changeQuantityForProduct(WebElement product, String newQuantity) {
        WebElement quantityBox = product.findElement(By.xpath("//input[contains(@type,'number')]"));

        quantityBox.sendKeys(Keys.DELETE);
        quantityBox.sendKeys(newQuantity);
        quantityBox.sendKeys(Keys.ENTER);
        SeleniumUtils.refreshPage(driver);
    }

    public String getDeliveryPrice() {

        if (deliveryPrice.getText().contains("lei")) {
            String priceWithoutSuffix = removeSuffix(deliveryPrice.getText());
            return priceWithoutSuffix;
        }

        return deliveryPrice.getText();
    }

    private String removeSuffix(String price) {
        return price.replace(" lei", "");
    }

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public String getCartSuccessMessage() {
        return cartSuccessMessage.getText();
    }

    public int getProductSubtotal(WebElement product) {
        String subtotal = product.findElement(By.cssSelector("#cart-items > li > div[class$='6u$(medium) 2u$ align-right'] > div:first-child")).getText();

        return Integer.valueOf(removeSuffix(subtotal));
    }

    public String getQuantityExceedsStockError() {
       return ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;",quantityExceedsStockError).toString();
    }

    public int getValueInQuantityBox(WebElement element) {
        return Integer.valueOf(element.findElement(By.cssSelector("#cart-items > li > div:nth-child(2) input")).getAttribute("value"));
    }

    public int getTotalPrice() {
        return Integer.valueOf(removeSuffix(totalPrice.getText()));
    }

    public String checkDeliveryPrice() {
        if (getTotalPrice() > 30000) {
            return "GRATUIT";
        } else {
            return "999";
        }
    }
}
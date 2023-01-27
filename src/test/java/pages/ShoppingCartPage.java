package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.List;

public class ShoppingCartPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//a[@class='color-theme-5 cart-remove-item']")
    private WebElement deleteButton;
    @FindBy(how = How.XPATH, using = "//input[contains(@type,'number')]")
    private WebElement quantityField;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public int removeFromCart() {
        deleteButton.click();
        getDriver().navigate().refresh();
        List<WebElement> itemsInCart = getDriver().findElements(By.xpath("//li[@class='row 25%']"));
        return itemsInCart.size();
    }

    public int getNoOfCartItems() {
        List<WebElement> itemsInCart = getDriver().findElements(By.xpath("//li[@class='row 25%']"));
        return itemsInCart.size();
    }

    public int changeQuantity(int qty) {
        quantityField.sendKeys(Keys.DELETE);
        quantityField.sendKeys(String.valueOf(qty));
        quantityField.sendKeys(Keys.ENTER);
        int value = Integer.valueOf(quantityField.getAttribute("value"));
        return value;
    }

    public double formatPrice(Integer price) {
        char[] chars = String.valueOf(price).toCharArray();
        StringBuilder integerPart = new StringBuilder();
        StringBuilder decimalPart = new StringBuilder();

        for (int i = 0; i < chars.length - 2; i++) {
            integerPart.append(chars[i]);
        }

        decimalPart.append(chars[chars.length - 2]);
        decimalPart.append(chars[chars.length - 1]);
        double finalNumber = Double.valueOf(integerPart + "." + decimalPart);
        df.format(finalNumber);
        return finalNumber;
    }

    public String getTotalPriceFormatted() {
        WebElement totalPriceOnSite = getDriver().findElement(By.cssSelector("div[class='12u$(large) 4u'] div[class='products-total line strong']"));
        String priceWithoutSuffix = removeSuffix(totalPriceOnSite.getText());
        Double formattedPriceWithoutSuffix = formatPrice(Integer.valueOf(priceWithoutSuffix));
        return df.format(formattedPriceWithoutSuffix);
    }

    public Object getDeliveryPriceFormatted() {
        String deliveryPrice = getDriver().findElement(By.cssSelector("div[class='12u$(large) 4u'] div[class='transport-total line']")).getText();

        if (deliveryPrice.contains("lei")) {
            String priceWithoutSuffix = removeSuffix(deliveryPrice);
            return formatPrice(Integer.valueOf(priceWithoutSuffix));
        }
        return deliveryPrice;
    }

    private String removeSuffix(String price) {
        return price.replace(" lei", "");
    }

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public String computeTotalCartPrice() {
        Double totalPrice = 0.0;
        List<WebElement> items = getDriver().findElements(By.xpath("//div[@class='color-theme-5 line']"));
        for (WebElement item : items) {
            String price = removeSuffix(item.getText());
            totalPrice += formatPrice(Integer.valueOf(price));
        }
        return df.format(totalPrice);
    }
}
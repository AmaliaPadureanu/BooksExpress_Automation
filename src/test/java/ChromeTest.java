import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ChromeTest {

    public static void main (String[] args) {

        System.setProperty("webdriver.chrome.driver", "/Users/Amalia/IdeaProjects/Drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.emag.ro");

        System.out.println(driver.getTitle());

        driver.navigate().to("https://www.youtube.com");

        driver.navigate().back();

        driver.navigate().forward();

        driver.quit();

    }
 }

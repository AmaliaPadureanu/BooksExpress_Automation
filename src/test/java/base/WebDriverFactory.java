package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    private static final WebDriverFactory instance = new WebDriverFactory();

    private WebDriverFactory() {

    }

    public static WebDriverFactory getInstance() {
        return instance;
    }

    private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<>();

    public WebDriver getDriver(String browser) {
        WebDriver driver = null;
        setDriver(browser);
        if (threadedDriver.get() == null) {
            try {
                if (browser.equalsIgnoreCase("firefox")) {
                    driver = new FirefoxDriver();
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase("chrome")) {
                    driver = new ChromeDriver();
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase("edge")) {
                    driver = new EdgeDriver();
                    threadedDriver.set(driver);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            threadedDriver.get().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            threadedDriver.get().manage().window().maximize();
        }
        return threadedDriver.get();
    }

    public void quitDriver() {
        threadedDriver.get().quit();
        threadedDriver.set(null);
    }

    private void setDriver(String browser) {
        String driverPath = "";
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        System.out.println("OS name from system property :: " + os);
        String directory = System.getProperty("user.dir") +  "//drivers//";
        String driverKey = "";
        String driverValue = "";

        if (browser.equalsIgnoreCase("firefox")) {
            driverKey = "webdriver.gecko.driver";
            driverValue = "geckodriver";
        } else if (browser.equalsIgnoreCase("chrome")) {
            driverKey = "webdriver.chrome.driver";
            driverValue = "chromedriver";
        } else if (browser.equalsIgnoreCase("edge")) {
            driverKey = "webdriver.edge.driver";
            driverValue = "msedgedriver";
        } else {
            System.out.println("Browser type not supported");
        }

        driverPath = directory + driverValue + (os.equals("win") ? ".exe" : "");
        System.out.println("Driver binary :: " + driverPath);
        System.setProperty(driverKey, driverPath);
    }
}

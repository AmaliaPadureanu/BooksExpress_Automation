package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class SeleniumUtils {

    public static String takeScreenshot(WebDriver driver) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String filename = ConstantUtils.SCREENSHOT_FOLDER + "screenshot " + timestamp.getTime() + ".png";
        try {
            FileUtils.copyFile(screenshotFile, new File(filename));
        } catch (IOException e) {
            System.out.println("File could not be saved on the disk!!");
        }
        return filename;
    }

    public static String getReportName() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return "extentReport " + timestamp.getTime() + ".html";
    }

    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public static String getCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public static String getCurrentPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public static void navigateBack(WebDriver driver) {
        driver.navigate().back();
    }
}

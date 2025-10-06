package com.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "reports/screenshots/";

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            File directory = new File(SCREENSHOT_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // Capture screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            String destination = SCREENSHOT_DIR + screenshotName + "_" + timestamp + ".png";
            File finalDestination = new File(destination);

            FileUtils.copyFile(source, finalDestination);

            return finalDestination.getAbsolutePath();
        } catch (Exception e) {
            System.out.println("Exception while capturing screenshot: " + e.getMessage());
            return null;
        }
    }

    public static String captureScreenshot(WebDriver driver) {
        return captureScreenshot(driver, "screenshot");
    }
}
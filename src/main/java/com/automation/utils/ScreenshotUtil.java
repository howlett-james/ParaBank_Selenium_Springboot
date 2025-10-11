package com.automation.utils;

import com.automation.constants.FrameworkConstants;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = FrameworkConstants.SCREENSHOTS_DIR;

    public static String captureFullPageScreenshot(WebDriver driver, String screenshotName) {
        try {
            File directory = new File(SCREENSHOT_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String destination = SCREENSHOT_DIR + screenshotName + "_full_" + timestamp + ".png";

            // Use AShot with viewportPasting to capture full page
            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(100))
                    .takeScreenshot(driver);

            ImageIO.write(screenshot.getImage(), "PNG", new File(destination));

            return new File(destination).getAbsolutePath();
        } catch (Exception e) {
            System.out.println("Exception while capturing full-page screenshot: " + e.getMessage());
            return null;
        }
    }
}

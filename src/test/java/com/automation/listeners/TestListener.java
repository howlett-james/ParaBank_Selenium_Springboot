package com.automation.listeners;

import com.automation.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("======================================");
        logger.info("TEST STARTED: " + result.getMethod().getMethodName());
        logger.info("======================================");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("TEST PASSED: " + result.getMethod().getMethodName());
        logger.info("Execution Time: " + (result.getEndMillis() - result.getStartMillis()) + "ms");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("TEST FAILED: " + result.getMethod().getMethodName());
        logger.error("Failure Reason: " + result.getThrowable());

        // Capture screenshot on failure
        Object testClass = result.getInstance();
        try {
            WebDriver driver = (WebDriver) testClass.getClass()
                    .getMethod("getDriver")
                    .invoke(testClass);

            if (driver != null) {
                String screenshotPath = ScreenshotUtil.captureFullPageScreenshot(
                        driver,
                        result.getMethod().getMethodName()
                );
                logger.info("Full-page screenshot captured: " + screenshotPath);
            }
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("TEST SKIPPED: " + result.getMethod().getMethodName());
        logger.warn("Skip Reason: " + result.getThrowable());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("======================================");
        logger.info("TEST SUITE STARTED: " + context.getName());
        logger.info("======================================");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("======================================");
        logger.info("TEST SUITE FINISHED: " + context.getName());
        logger.info("Total Tests: " + context.getAllTestMethods().length);
        logger.info("Passed: " + context.getPassedTests().size());
        logger.info("Failed: " + context.getFailedTests().size());
        logger.info("Skipped: " + context.getSkippedTests().size());
        logger.info("======================================");
    }
}
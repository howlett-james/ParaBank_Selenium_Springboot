package com.automation.tests;

import com.automation.config.WebDriverConfig;
import com.automation.listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

@SpringBootTest(classes = WebDriverConfig.class)
@Listeners(TestListener.class)
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApplicationContext applicationContext;

    protected WebDriver driver;

    @Value("${app.url:https://parabank.parasoft.com/parabank/index.htm}")
    protected String baseUrl;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Get a new WebDriver instance from Spring context for each test
        driver = applicationContext.getBean(WebDriver.class);
        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error while quitting driver: " + e.getMessage());
            }
            driver = null;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
package com.automation.tests;

import com.automation.config.WebDriverConfig;
import com.automation.constants.FrameworkConstants;
import com.automation.listeners.TestListener;
import com.automation.utils.JsonReaderUtil;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

import java.io.File;
import java.util.Map;

@SpringBootTest(classes = WebDriverConfig.class)
@Listeners(TestListener.class)
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApplicationContext applicationContext;

    protected WebDriver driver;

    @Value("${app.url:https://parabank.parasoft.com/parabank/index.htm}")
    protected String baseUrl;

    @Value("${test.username}")
    private String defaultUsername;

    @Value("${test.password}")
    private String defaultPassword;

    private String username;
    private String password;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
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

    protected void loadCredentials() {
        try {
            File file = new File(FrameworkConstants.TESTDATA_FILE);

            if (!file.exists() || file.length() == 0) {
                username = null;
                password = null;
                System.out.println("[INFO] No registered user found. Registration test should run first.");
                return;
            }

            Map<String, Object> randomUser = JsonReaderUtil.getRandomRecord(file);

            if (randomUser != null && randomUser.containsKey("username") && randomUser.containsKey("password")) {
                username = randomUser.get("username").toString();
                password = randomUser.get("password").toString();
                System.out.println("[INFO] Loaded credentials from testdata.json: " + username);
            } else {
                username = null;
                password = null;
                System.out.println("[INFO] No registered user found. Registration test should run first.");
            }

        } catch (Exception e) {
            username = null;
            password = null;
            System.err.println("[ERROR] Failed to load credentials: " + e.getMessage());
        }
    }

    protected String getUsername() {
        return username;
    }

    protected String getPassword() {
        return password;
    }
}

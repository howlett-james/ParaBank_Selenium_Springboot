package com.automation.tests;

import com.automation.config.WebDriverConfig;
import com.automation.listeners.TestListener;
import com.automation.utils.JsonReaderUtil;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

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
        // Get WebDriver from Spring Context
        driver = applicationContext.getBean(WebDriver.class);
        driver.get(baseUrl);

        loadCredentials(); // load before tests
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

    // ---------- Credential Handling ----------

    private void loadCredentials() {
        Map<String, Object> latestUser = JsonReaderUtil.getLatestRecord("src/test/resources/testdata.json");

        if (latestUser != null && latestUser.containsKey("username") && latestUser.containsKey("password")) {
            username = latestUser.get("username").toString();
            password = latestUser.get("password").toString();
            System.out.println("[INFO] Loaded credentials from testdata.json: " + username);
        } else {
            username = defaultUsername;
            password = defaultPassword;
            System.out.println("[INFO] Using fallback credentials from application.properties");
        }
    }

    protected String getUsername() {
        return username;
    }

    protected String getPassword() {
        return password;
    }
}

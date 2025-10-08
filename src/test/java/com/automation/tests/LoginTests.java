package com.automation.tests;

import com.automation.pages.AccountsOverviewPage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("classpath:application.properties")
public class LoginTests extends BaseTest {

    private LoginPage loginPage;
    private AccountsOverviewPage accountsPage;

    @Value("${test.username}")
    private String username;

    @Value("${test.password}")
    private String password;

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1, description = "Verify successful login with valid credentials" )
    public void testSuccessfulLogin() {
        accountsPage = loginPage.login(username, password);

        Assert.assertTrue(accountsPage.isAccountsOverviewPageDisplayed(),
                "Accounts Overview page should be displayed after successful login");
        Assert.assertTrue(accountsPage.isLoggedIn(),
                "User should be logged in");
    }

    @Test(priority = 2, description = "Verify login fails with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        loginPage.login("invaliduser", "invalidpass");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid credentials");
    }

    @Test(priority = 3, description = "Verify login with empty username")
    public void testLoginWithEmptyUsername() {
        loginPage.login("", password);

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for empty username");
    }

    @Test(priority = 4, description = "Verify login with empty password")
    public void testLoginWithEmptyPassword() {
        loginPage.login(username, "");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for empty password");
    }

    @Test(priority = 5, description = "Verify login page is displayed correctly")
    public void testLoginPageDisplay() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Login page should be displayed");
        Assert.assertEquals(driver.getTitle(), "ParaBank | Welcome | Online Banking",
                "Page title should match");
    }

    @Test(priority = 6, description = "Verify successful logout")
    public void testLogout() {
        accountsPage = loginPage.login(username, password);
        Assert.assertTrue(accountsPage.isLoggedIn(), "User should be logged in");

        accountsPage.logout();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Login page should be displayed after logout");
    }
}

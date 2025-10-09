package com.automation.tests;

import com.automation.pages.AccountsOverviewPage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;
    private AccountsOverviewPage accountsPage;

    @BeforeMethod(alwaysRun = true)
    public void initPages(Method method) {
        loginPage = new LoginPage(driver);

        loadCredentials();
    }

    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        if (getUsername() == null || getPassword() == null) {
            throw new RuntimeException("No registered user found. Run registration first.");
        }

        accountsPage = loginPage.login(getUsername(), getPassword());

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
        loginPage.login("", getPassword());
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for empty username");
    }

    @Test(priority = 4, description = "Verify login with empty password")
    public void testLoginWithEmptyPassword() {
        loginPage.login(getUsername(), "");
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
        if (getUsername() == null || getPassword() == null) {
            throw new RuntimeException("No registered user found. Run registration first.");
        }

        accountsPage = loginPage.login(getUsername(), getPassword());
        Assert.assertTrue(accountsPage.isLoggedIn(), "User should be logged in");

        accountsPage.logout();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Login page should be displayed after logout");
    }
}
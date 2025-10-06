package com.automation.tests;

import com.automation.pages.AccountsOverviewPage;
import com.automation.pages.LoginPage;
import com.automation.pages.OpenNewAccountPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AccountTests extends BaseTest {

    private LoginPage loginPage;
    private AccountsOverviewPage accountsPage;

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(driver);
        // Login before each test
        accountsPage = loginPage.login("john", "demo");
    }

    @Test(priority = 1, description = "Verify accounts overview page displays accounts")
    public void testAccountsOverviewDisplay() {
        Assert.assertTrue(accountsPage.isAccountsOverviewPageDisplayed(),
                "Accounts Overview page should be displayed");
        Assert.assertTrue(accountsPage.getNumberOfAccounts() > 0,
                "At least one account should be displayed");
    }

    @Test(priority = 2, description = "Verify user can open a new savings account")
    public void testOpenNewSavingsAccount() {
        OpenNewAccountPage openAccountPage = accountsPage.clickOpenNewAccount();

        Assert.assertTrue(openAccountPage.isOpenNewAccountPageDisplayed(),
                "Open New Account page should be displayed");

        openAccountPage.openNewAccount("SAVINGS");

        Assert.assertTrue(openAccountPage.isAccountOpenedSuccessfully(),
                "New account should be opened successfully");
        Assert.assertFalse(openAccountPage.getNewAccountId().isEmpty(),
                "New account ID should be generated");
    }

    @Test(priority = 3, description = "Verify user can open a new checking account")
    public void testOpenNewCheckingAccount() {
        OpenNewAccountPage openAccountPage = accountsPage.clickOpenNewAccount();
        openAccountPage.openNewAccount("CHECKING");

        Assert.assertTrue(openAccountPage.isAccountOpenedSuccessfully(),
                "New checking account should be opened successfully");
    }

    @Test(priority = 4, description = "Verify welcome message displays username")
    public void testWelcomeMessageDisplay() {
        String welcomeMsg = accountsPage.getWelcomeMessage();
        Assert.assertTrue(welcomeMsg.contains("Welcome"),
                "Welcome message should be displayed");
    }
}
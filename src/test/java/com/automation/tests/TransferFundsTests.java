package com.automation.tests;

import com.automation.pages.AccountsOverviewPage;
import com.automation.pages.LoginPage;
import com.automation.pages.TransferFundsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TransferFundsTests extends BaseTest {

    private LoginPage loginPage;
    private AccountsOverviewPage accountsPage;
    private TransferFundsPage transferPage;

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(driver);
        accountsPage = loginPage.login("john", "demo");
        transferPage = accountsPage.clickTransferFunds();
    }

    @Test(priority = 1, description = "Verify transfer funds page is displayed")
    public void testTransferFundsPageDisplay() {
        Assert.assertTrue(transferPage.isTransferFundsPageDisplayed(),
                "Transfer Funds page should be displayed");
    }

    @Test(priority = 2, description = "Verify successful fund transfer")
    public void testSuccessfulFundTransfer() {
        transferPage.enterAmount("100");
        transferPage.clickTransferButton();

        Assert.assertTrue(transferPage.isTransferSuccessful(),
                "Fund transfer should be successful");
    }

    @Test(priority = 3, description = "Verify transfer with zero amount")
    public void testTransferWithZeroAmount() {
        transferPage.enterAmount("0");
        transferPage.clickTransferButton();

        Assert.assertFalse(transferPage.isTransferSuccessful(),
                "Transfer with zero amount should not succeed");
    }

    @Test(priority = 4, description = "Verify transfer with negative amount")
    public void testTransferWithNegativeAmount() {
        transferPage.enterAmount("-50");
        transferPage.clickTransferButton();

        Assert.assertFalse(transferPage.isTransferSuccessful(),
                "Transfer with negative amount should not succeed");
    }
}
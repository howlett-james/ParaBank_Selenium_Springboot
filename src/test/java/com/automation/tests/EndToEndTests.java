package com.automation.tests;

import com.automation.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EndToEndTests extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private AccountsOverviewPage accountsPage;
    private TransferFundsPage transferFundsPage;
    private BillPayPage billPayPage;
    private OpenNewAccountPage openNewAccountPage;

    @BeforeMethod
    public void initPages() {
        homePage = new HomePage(driver);
        loginPage = homePage.clickLogin();
        accountsPage = loginPage.login("john", "demo");
    }

    @Test(priority = 1, description = "End-to-end test: Open new account and transfer funds")
    public void testOpenAccountAndTransferFunds() {
        // Open new savings account
        openNewAccountPage = accountsPage.clickOpenNewAccount();
        openNewAccountPage.openNewAccount("SAVINGS");
        Assert.assertTrue(openNewAccountPage.isAccountOpenedSuccessfully(), "New savings account should be created");

        // Transfer funds
        transferFundsPage = accountsPage.clickTransferFunds();
        transferFundsPage.transferFunds("50", "12345", "67890"); // replace account IDs as per actual
        Assert.assertTrue(transferFundsPage.isTransferSuccessful(), "Fund transfer should be successful");
    }

    @Test(priority = 2, description = "End-to-end test: Pay bill successfully")
    public void testBillPayment() {
        billPayPage = accountsPage.clickBillPay();
        billPayPage.fillPayeeInformation(
                "Electric Company",
                "123 Main St",
                "Springfield",
                "IL",
                "62701",
                "555-1234",
                "12345"
        );
        billPayPage.enterAmount("100");
        billPayPage.clickSendPayment();

        Assert.assertTrue(billPayPage.isPaymentSuccessful(), "Bill payment should be successful");
    }
}

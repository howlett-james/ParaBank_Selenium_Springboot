package com.automation.tests;

import com.automation.pages.AccountsOverviewPage;
import com.automation.pages.BillPayPage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BillPayTests extends BaseTest {

    private LoginPage loginPage;
    private AccountsOverviewPage accountsPage;
    private BillPayPage billPayPage;

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(driver);
        accountsPage = loginPage.login("john", "demo");
        billPayPage = accountsPage.clickBillPay();
    }

    @Test(priority = 1, description = "Verify bill pay page is displayed")
    public void testBillPayPageDisplay() {
        Assert.assertTrue(billPayPage.isBillPayPageDisplayed(),
                "Bill Pay page should be displayed");
    }

    @Test(priority = 2, description = "Verify successful bill payment")
    public void testSuccessfulBillPayment() {
        billPayPage.fillPayeeInformation(
                "Electric Company",
                "123 Main St",
                "Springfield",
                "IL",
                "62701",
                "555-1234",
                "12345"
        );
        billPayPage.enterAmount("150");
        billPayPage.clickSendPayment();

        Assert.assertTrue(billPayPage.isPaymentSuccessful(),
                "Bill payment should be successful");
    }

    @Test(priority = 3, description = "Verify bill payment with missing payee name")
    public void testBillPaymentWithMissingPayeeName() {
        billPayPage.fillPayeeInformation(
                "",
                "123 Main St",
                "Springfield",
                "IL",
                "62701",
                "555-1234",
                "12345"
        );
        billPayPage.enterAmount("100");
        billPayPage.clickSendPayment();

        Assert.assertFalse(billPayPage.isPaymentSuccessful(),
                "Payment should not succeed without payee name");
    }

    @Test(priority = 4, description = "Verify bill payment with invalid amount")
    public void testBillPaymentWithInvalidAmount() {
        billPayPage.fillPayeeInformation(
                "Water Company",
                "456 Oak Ave",
                "Chicago",
                "IL",
                "60601",
                "555-5678",
                "67890"
        );
        billPayPage.enterAmount("0");
        billPayPage.clickSendPayment();

        Assert.assertFalse(billPayPage.isPaymentSuccessful(),
                "Payment should not succeed with zero amount");
    }
}
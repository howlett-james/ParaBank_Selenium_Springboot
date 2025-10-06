package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class DashboardPage extends BasePage {

    @FindBy(linkText = "Accounts Overview")
    private WebElement accountsOverviewLink;

    @FindBy(linkText = "Transfer Funds")
    private WebElement transferFundsLink;

    @FindBy(linkText = "Bill Pay")
    private WebElement billPayLink;

    @FindBy(xpath = "//h2[contains(text(),'Dashboard')]")
    private WebElement dashboardHeader;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardDisplayed() {
        return isElementDisplayed(dashboardHeader);
    }

    public AccountsOverviewPage clickAccountsOverview() {
        click(accountsOverviewLink);
        return new AccountsOverviewPage(driver);
    }

    public TransferFundsPage clickTransferFunds() {
        click(transferFundsLink);
        return new TransferFundsPage(driver);
    }

    public BillPayPage clickBillPay() {
        click(billPayLink);
        return new BillPayPage(driver);
    }
}
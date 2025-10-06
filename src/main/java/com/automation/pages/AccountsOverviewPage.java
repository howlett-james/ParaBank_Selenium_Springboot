package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountsOverviewPage extends BasePage {

    @FindBy(css = "#showOverview h1.title")
    private WebElement pageHeader;

    @FindBy(linkText = "Open New Account")
    private WebElement openNewAccountLink;

    @FindBy(linkText = "Transfer Funds")
    private WebElement transferFundsLink;

    @FindBy(linkText = "Bill Pay")
    private WebElement billPayLink;

    @FindBy(linkText = "Find Transactions")
    private WebElement findTransactionsLink;

    @FindBy(linkText = "Update Contact Info")
    private WebElement updateContactInfoLink;

    @FindBy(linkText = "Request Loan")
    private WebElement requestLoanLink;

    @FindBy(linkText = "Log Out")
    private WebElement logoutLink;

    @FindBy(xpath = "//table[@id='accountTable']//tbody//tr")
    private List<WebElement> accountRows;

    @FindBy(xpath = "//b[contains(text(),'Welcome')]")
    private WebElement welcomeMessage;

    @FindBy(xpath = "//td[contains(text(),'$')]")
    private List<WebElement> accountBalances;

    public AccountsOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountsOverviewPageDisplayed() {
        return isElementDisplayed(pageHeader);
    }

    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }

    public int getNumberOfAccounts() {
        return accountRows.size();
    }

    public OpenNewAccountPage clickOpenNewAccount() {
        click(openNewAccountLink);
        return new OpenNewAccountPage(driver);
    }

    public TransferFundsPage clickTransferFunds() {
        click(transferFundsLink);
        return new TransferFundsPage(driver);
    }

    public BillPayPage clickBillPay() {
        click(billPayLink);
        return new BillPayPage(driver);
    }

    public void logout() {
        click(logoutLink);
    }

    public boolean isLoggedIn() {
        return isElementDisplayed(welcomeMessage);
    }
}
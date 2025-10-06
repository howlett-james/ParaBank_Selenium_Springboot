package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

@Component
public class TransferFundsPage extends BasePage {

    @FindBy(id = "amount")
    private WebElement amountField;

    @FindBy(id = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(id = "toAccountId")
    private WebElement toAccountDropdown;

    @FindBy(xpath = "//input[@value='Transfer']")
    private WebElement transferButton;

    @FindBy(xpath = "//h1[text()='Transfer Funds']")
    private WebElement pageHeader;

    @FindBy(xpath = "//h1[text()='Transfer Complete!']")
    private WebElement successHeader;

    @FindBy(xpath = "//span[@id='amount']")
    private WebElement transferredAmount;

    @FindBy(xpath = "//span[@id='fromAccountId']")
    private WebElement fromAccountId;

    @FindBy(xpath = "//span[@id='toAccountId']")
    private WebElement toAccountId;

    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    public void enterAmount(String amount) {
        sendKeys(amountField, amount);
    }

    public void selectFromAccount(String accountId) {
        Select select = new Select(fromAccountDropdown);
        select.selectByValue(accountId);
    }

    public void selectToAccount(String accountId) {
        Select select = new Select(toAccountDropdown);
        select.selectByValue(accountId);
    }

    public void clickTransferButton() {
        click(transferButton);
    }

    public void transferFunds(String amount, String fromAccount, String toAccount) {
        enterAmount(amount);
        selectFromAccount(fromAccount);
        selectToAccount(toAccount);
        clickTransferButton();
    }

    public boolean isTransferSuccessful() {
        return isElementDisplayed(successHeader);
    }

    public boolean isTransferFundsPageDisplayed() {
        return isElementDisplayed(pageHeader);
    }
}
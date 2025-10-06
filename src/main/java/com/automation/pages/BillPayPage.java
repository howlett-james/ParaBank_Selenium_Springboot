package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class BillPayPage extends BasePage {

    @FindBy(name = "payee.name")
    private WebElement payeeNameField;

    @FindBy(name = "payee.address.street")
    private WebElement payeeAddressField;

    @FindBy(name = "payee.address.city")
    private WebElement payeeCityField;

    @FindBy(name = "payee.address.state")
    private WebElement payeeStateField;

    @FindBy(name = "payee.address.zipCode")
    private WebElement payeeZipCodeField;

    @FindBy(name = "payee.phoneNumber")
    private WebElement payeePhoneField;

    @FindBy(name = "payee.accountNumber")
    private WebElement payeeAccountField;

    @FindBy(name = "verifyAccount")
    private WebElement verifyAccountField;

    @FindBy(name = "amount")
    private WebElement amountField;

    @FindBy(name = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(xpath = "//input[@value='Send Payment']")
    private WebElement sendPaymentButton;

    @FindBy(xpath = "//h1[text()='Bill Payment Service']")
    private WebElement pageHeader;

    @FindBy(xpath = "//h1[text()='Bill Payment Complete']")
    private WebElement successHeader;

    @FindBy(xpath = "//span[@class='error']")
    private WebElement errorMessage;

    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    public void fillPayeeInformation(String name, String address, String city,
                                     String state, String zipCode, String phone,
                                     String account) {
        sendKeys(payeeNameField, name);
        sendKeys(payeeAddressField, address);
        sendKeys(payeeCityField, city);
        sendKeys(payeeStateField, state);
        sendKeys(payeeZipCodeField, zipCode);
        sendKeys(payeePhoneField, phone);
        sendKeys(payeeAccountField, account);
        sendKeys(verifyAccountField, account);
    }

    public void enterAmount(String amount) {
        sendKeys(amountField, amount);
    }

    public void clickSendPayment() {
        click(sendPaymentButton);
    }

    public boolean isPaymentSuccessful() {
        return isElementDisplayed(successHeader);
    }

    public boolean isBillPayPageDisplayed() {
        return isElementDisplayed(pageHeader);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
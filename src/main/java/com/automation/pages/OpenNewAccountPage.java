package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

@Component
public class OpenNewAccountPage extends BasePage {

    @FindBy(id = "type")
    private WebElement accountTypeDropdown;

    @FindBy(id = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(xpath = "//input[@value='Open New Account']")
    private WebElement openAccountButton;

    @FindBy(xpath = "//h1[text()='Open New Account']")
    private WebElement pageHeader;

    @FindBy(xpath = "//h1[text()='Account Opened!']")
    private WebElement successHeader;

    @FindBy(id = "newAccountId")
    private WebElement newAccountIdLink;

    @FindBy(xpath = "//p[contains(text(),'Congratulations')]")
    private WebElement congratulationsMessage;

    public OpenNewAccountPage(WebDriver driver) {
        super(driver);
    }

    public void selectAccountType(String accountType) {
        Select select = new Select(accountTypeDropdown);
        select.selectByVisibleText(accountType);
    }

    public void selectFromAccount(String accountId) {
        Select select = new Select(fromAccountDropdown);
        select.selectByValue(accountId);
    }

    public void clickOpenNewAccount() {
        click(openAccountButton);
    }

    public void openNewAccount(String accountType) {
        selectAccountType(accountType);
        clickOpenNewAccount();
    }

    public boolean isAccountOpenedSuccessfully() {
        return isElementDisplayed(successHeader);
    }

    public String getNewAccountId() {
        return getText(newAccountIdLink);
    }

    public boolean isOpenNewAccountPageDisplayed() {
        return isElementDisplayed(pageHeader);
    }
}
package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(css = ".login [value=\"Log In\"]")
    private WebElement loginButton;

    @FindBy(css = ".error")
    private WebElement errorMessage;

    @FindBy(linkText = "Register")
    private WebElement registerLink;

    @FindBy(linkText = "Forgot login info?")
    private WebElement forgotLoginLink;

    @FindBy(xpath = "//h2[text()='Customer Login']")
    private WebElement loginHeader;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public AccountsOverviewPage clickLoginButton() {
        click(loginButton);
        return new AccountsOverviewPage(driver);
    }

    public AccountsOverviewPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public RegisterPage clickRegisterLink() {
        click(registerLink);
        return new RegisterPage(driver);
    }

    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(loginHeader);
    }
}
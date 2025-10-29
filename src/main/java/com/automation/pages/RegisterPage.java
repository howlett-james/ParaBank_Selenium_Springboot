package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class RegisterPage extends BasePage {

    @FindBy(id = "customer.firstName")
    private WebElement firstNameField;

    @FindBy(id = "customer.lastName")
    private WebElement lastNameField;

    @FindBy(id = "customer.address.street")
    private WebElement addressField;

    @FindBy(id = "customer.address.city")
    private WebElement cityField;

    @FindBy(id = "customer.address.state")
    private WebElement stateField;

    @FindBy(id = "customer.address.zipCode")
    private WebElement zipCodeField;

    @FindBy(id = "customer.phoneNumber")
    private WebElement phoneNumberField;

    @FindBy(id = "customer.ssn")
    private WebElement ssnField;

    @FindBy(id = "customer.username")
    private WebElement usernameField;

    @FindBy(id = "customer.password")
    private WebElement passwordField;

    @FindBy(id = "repeatedPassword")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@value='Register']")
    private WebElement registerButton;

    @FindBy(xpath = "//h1[text()='Signing up is easy!']")
    private WebElement pageHeader;

    @FindBy(css = "#rightPanel > p")
    private WebElement successMessage;

    @FindBy(css = "#customer.username.errors")
    private WebElement errorMessage;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void fillRegistrationForm(String firstName, String lastName, String address,
                                     String city, String state, String zipCode,
                                     String phone, String ssn, String username,
                                     String password) {
        sendKeys(firstNameField, firstName);
        sendKeys(lastNameField, lastName);
        sendKeys(addressField, address);
        sendKeys(cityField, city);
        sendKeys(stateField, state);
        sendKeys(zipCodeField, zipCode);
        sendKeys(phoneNumberField, phone);
        sendKeys(ssnField, ssn);
        sendKeys(usernameField, username);
        sendKeys(passwordField, password);
        sendKeys(confirmPasswordField, password);
    }

    public void clickRegisterButton() {
        click(registerButton);
    }

    public boolean isRegistrationSuccessful() {
        if (getPageTitle().contains("Customer Created")) {
            return true;
        }
        return false;
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public boolean isRegistrationPageDisplayed() {
        return isElementDisplayed(pageHeader);
    }
}
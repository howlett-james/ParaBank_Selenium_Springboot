package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends BasePage {

    @FindBy(linkText = "Register")
    private WebElement registerLink;

    @FindBy(linkText = "Log In")
    private WebElement loginLink;

    @FindBy(xpath = "//h1[text()='Welcome to ParaBank']")
    private WebElement welcomeHeader;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHomePageDisplayed() {
        return isElementDisplayed(welcomeHeader);
    }

    public LoginPage clickLogin() {
        click(loginLink);
        return new LoginPage(driver);
    }

    public RegisterPage clickRegister() {
        click(registerLink);
        return new RegisterPage(driver);
    }
}

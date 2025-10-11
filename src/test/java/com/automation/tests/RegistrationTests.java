package com.automation.tests;

import com.automation.pages.LoginPage;
import com.automation.pages.RegisterPage;
import com.automation.utils.TestDataGenerator;
import com.automation.utils.TestDataWriter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class RegistrationTests extends BaseTest {

    private LoginPage loginPage;
    private RegisterPage registerPage;

    @BeforeMethod(alwaysRun = true)
    public void initPages() {
        loginPage = new LoginPage(driver);
        registerPage = loginPage.clickRegisterLink();
    }

    @Test(priority = 1, description = "Verify registration page is displayed")
    public void testRegistrationPageDisplay() {
        Assert.assertTrue(registerPage.isRegistrationPageDisplayed(),
                "Registration page should be displayed");
        Assert.assertTrue(driver.getCurrentUrl().contains("register"),
                "URL should contain 'register'");
    }

    @Test(priority = 2, description = "Verify successful user registration with valid data")
    public void testSuccessfulRegistration() {
        Map<String, String> userData = TestDataGenerator.generateRegistrationData();

        registerPage.fillRegistrationForm(
                userData.get("firstName"),
                userData.get("lastName"),
                userData.get("address"),
                userData.get("city"),
                userData.get("state"),
                userData.get("zipCode"),
                userData.get("phone"),
                userData.get("ssn"),
                userData.get("username"),
                userData.get("password")
        );

        registerPage.clickRegisterButton();

        if (registerPage.isRegistrationSuccessful()) {
            TestDataWriter.appendUserData(
                    userData.get("username"),
                    userData.get("password")
            );
        }

        Assert.assertTrue(registerPage.isRegistrationSuccessful(),
                "Registration should be successful");
        Assert.assertTrue(registerPage.getSuccessMessage().contains("successfully"),
                "Success message should be displayed");
    }

@Test(priority = 3, description = "Verify registration with all mandatory fields")
    public void testRegistrationWithMandatoryFields() {
        Map<String, String> userData = TestDataGenerator.generateRegistrationData();

        registerPage.fillRegistrationForm(
                userData.get("firstName"),
                userData.get("lastName"),
                userData.get("address"),
                userData.get("city"),
                userData.get("state"),
                userData.get("zipCode"),
                userData.get("phone"),
                userData.get("ssn"),
                userData.get("username"),
                userData.get("password")
        );

        registerPage.clickRegisterButton();

        Assert.assertTrue(registerPage.isRegistrationSuccessful(),
                "Registration should be successful with all mandatory fields");
    }

    @Test(priority = 4, description = "Verify registration fails with empty first name")
    public void testRegistrationWithEmptyFirstName() {
        Map<String, String> userData = TestDataGenerator.generateRegistrationData();

        registerPage.fillRegistrationForm(
                "",
                userData.get("lastName"),
                userData.get("address"),
                userData.get("city"),
                userData.get("state"),
                userData.get("zipCode"),
                userData.get("phone"),
                userData.get("ssn"),
                userData.get("username"),
                userData.get("password")
        );

        registerPage.clickRegisterButton();

        Assert.assertTrue(registerPage.isRegistrationPageDisplayed() ||
                        driver.getCurrentUrl().contains("register"),
                "Should remain on registration page with validation error");
    }

    @Test(priority = 5, description = "Verify registration fails with empty last name")
    public void testRegistrationWithEmptyLastName() {
        Map<String, String> userData = TestDataGenerator.generateRegistrationData();

        registerPage.fillRegistrationForm(
                userData.get("firstName"),
                "",
                userData.get("address"),
                userData.get("city"),
                userData.get("state"),
                userData.get("zipCode"),
                userData.get("phone"),
                userData.get("ssn"),
                userData.get("username"),
                userData.get("password")
        );

        registerPage.clickRegisterButton();

        Assert.assertTrue(registerPage.isRegistrationPageDisplayed() ||
                        driver.getCurrentUrl().contains("register"),
                "Should remain on registration page with validation error");
    }

    @Test(priority = 6, description = "Verify registration fails with empty address")
    public void testRegistrationWithEmptyAddress() {
        Map<String, String> userData = TestDataGenerator.generateRegistrationData();

        registerPage.fillRegistrationForm(
                userData.get("firstName"),
                userData.get("lastName"),
                "",
                userData.get("city"),
                userData.get("state"),
                userData.get("zipCode"),
                userData.get("phone"),
                userData.get("ssn"),
                userData.get("username"),
                userData.get("password")
        );

        registerPage.clickRegisterButton();

        Assert.assertTrue(registerPage.isRegistrationPageDisplayed() ||
                        driver.getCurrentUrl().contains("register"),
                "Should remain on registration page with validation error");
    }

    @Test(priority = 7, description = "Verify registration fails with empty username")
    public void testRegistrationWithEmptyUsername() {
        Map<String, String> userData = TestDataGenerator.generateRegistrationData();

        registerPage.fillRegistrationForm(
                userData.get("firstName"),
                userData.get("lastName"),
                userData.get("address"),
                userData.get("city"),
                userData.get("state"),
                userData.get("zipCode"),
                userData.get("phone"),
                userData.get("ssn"),
                "",
                userData.get("password")
        );

        registerPage.clickRegisterButton();

        Assert.assertTrue(registerPage.isRegistrationPageDisplayed() ||
                        driver.getCurrentUrl().contains("register"),
                "Should remain on registration page with validation error");
    }

    @Test(priority = 8, description = "Verify registration fails with empty password")
    public void testRegistrationWithEmptyPassword() {
        Map<String, String> userData = TestDataGenerator.generateRegistrationData();

        registerPage.fillRegistrationForm(
                userData.get("firstName"),
                userData.get("lastName"),
                userData.get("address"),
                userData.get("city"),
                userData.get("state"),
                userData.get("zipCode"),
                userData.get("phone"),
                userData.get("ssn"),
                userData.get("username"),
                ""
        );

        registerPage.clickRegisterButton();

        Assert.assertTrue(registerPage.isRegistrationPageDisplayed() ||
                        driver.getCurrentUrl().contains("register"),
                "Should remain on registration page with validation error");
    }

    @Test(priority = 9, description = "Verify registration with special characters in name")
    public void testRegistrationWithSpecialCharacters() {
        Map<String, String> userData = TestDataGenerator.generateRegistrationData();

        registerPage.fillRegistrationForm(
                userData.get("firstName") + "@123",
                userData.get("lastName") + "#456",
                userData.get("address"),
                userData.get("city"),
                userData.get("state"),
                userData.get("zipCode"),
                userData.get("phone"),
                userData.get("ssn"),
                userData.get("username"),
                userData.get("password")
        );

        registerPage.clickRegisterButton();

        boolean isOnRegisterPage = registerPage.isRegistrationPageDisplayed() ||
                driver.getCurrentUrl().contains("register");
        boolean isRegistered = registerPage.isRegistrationSuccessful();

        Assert.assertTrue(isOnRegisterPage || isRegistered,
                "Should either show error or complete registration");
    }
}
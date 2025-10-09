package com.automation.utils;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class TestDataGenerator {

    private static final Faker faker = new Faker();

    /**
     * Generate complete registration data as Map
     */
    public static Map<String, String> generateRegistrationData() {
        Map<String, String> data = new HashMap<>();

        data.put("firstName", generateFirstName());
        data.put("lastName", generateLastName());
        data.put("address", generateStreetAddress());
        data.put("city", generateCity());
        data.put("state", generateZipCode());
        data.put("zipCode", generateZipCode());
        data.put("phone", generatePhoneNumber());
        data.put("ssn", generateSSN());
        data.put("username", generateUniqueUsername());
        data.put("password", generatePassword());

        return data;
    }

    /**
     * Generate email address
     */
    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    /**
     * Generate company name for payee
     */
    public static String generateCompanyName() {
        return faker.company().name();
    }

    /**
     * Generate phone number in format ###-###-####
     */
    public static String generatePhoneNumber() {
        return faker.numerify("###-###-####");
    }

    /**
     * Generate SSN in format ###-##-####
     */
    public static String generateSSN() {
        return faker.numerify("###-##-####");
    }

    /**
     * Generate 5-digit zip code
     */
    public static String generateZipCode() {
        return faker.numerify("######");
    }

    /**
     * Generate amount for transactions
     */
    public static String generateAmount(int min, int max) {
        return String.valueOf(faker.number().numberBetween(min, max));
    }

    /**
     * Generate full address
     */
    public static String generateStreetAddress() {
        return faker.address().streetAddress();
    }

    /**
     * Generate random first name
     */
    public static String generateFirstName() {
        return faker.name().firstName();
    }

    /**
     * Generate random last name
     */
    public static String generateLastName() {
        return faker.name().lastName();
    }

    /**
     * Generate random city
     */
    public static String generateCity() {
        return faker.address().city();
    }

    /**
     * Generate random state abbreviation
     */
    public static String generateState() {
        return faker.address().stateAbbr();
    }
    /**
     * Generate unique username with timestamp
     */
    public static String generateUniqueUsername() {
        return faker.name().username() +"@"+ faker.numerify("#######") +"$";
    }

    /**
     * Generate strong password
     */
    public static String generatePassword() {
        return faker.internet().password(8, 16, true, true, true);
    }
}
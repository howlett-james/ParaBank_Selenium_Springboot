package com.automation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FailedTestCollector implements ITestListener {

    private Map<String, List<String>> failedTests = new HashMap<>();

    @Override
    public void onTestFailure(ITestResult result) {
        String className = result.getTestClass().getName();
        String methodName = result.getMethod().getMethodName();

        failedTests.computeIfAbsent(className, k -> new ArrayList<>()).add(methodName);

        System.out.println("Failed Test Collected: " + className + "." + methodName);
    }

    @Override
    public void onFinish(ITestContext context) {
        if (!failedTests.isEmpty()) {
            generateRerunXml();
        } else {
            System.out.println("No failed tests to rerun!");
        }
    }

    private void generateRerunXml() {
        String xmlPath = "src/test/resources/testng-rerun-failed.xml";

        try (FileWriter writer = new FileWriter(xmlPath)) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<!DOCTYPE suite SYSTEM \"https://testng.org/testng-1.0.dtd\">\n");
            writer.write("<suite name=\"Failed Tests Rerun Suite\" verbose=\"1\">\n\n");

            writer.write("    <listeners>\n");
            writer.write("        <listener class-name=\"com.automation.listeners.TestListener\"/>\n");
            writer.write("        <listener class-name=\"com.automation.listeners.ExtentReportListener\"/>\n");
            writer.write("    </listeners>\n\n");

            writer.write("    <test name=\"Rerun Failed Tests\">\n");
            writer.write("        <classes>\n");

            for (Map.Entry<String, List<String>> entry : failedTests.entrySet()) {
                writer.write("            <class name=\"" + entry.getKey() + "\">\n");
                writer.write("                <methods>\n");

                for (String method : entry.getValue()) {
                    writer.write("                    <include name=\"" + method + "\"/>\n");
                }

                writer.write("                </methods>\n");
                writer.write("            </class>\n");
            }

            writer.write("        </classes>\n");
            writer.write("    </test>\n\n");
            writer.write("</suite>");

            System.out.println("\n========================================");
            System.out.println("Failed tests XML generated: " + xmlPath);
            System.out.println("Total failed tests: " + failedTests.values().stream().mapToInt(List::size).sum());
            System.out.println("To rerun failed tests, execute:");
            System.out.println("./gradlew test -Dsuite=testng-rerun-failed");
            System.out.println("========================================\n");

        } catch (IOException e) {
            System.err.println("Error generating rerun XML: " + e.getMessage());
        }
    }
}
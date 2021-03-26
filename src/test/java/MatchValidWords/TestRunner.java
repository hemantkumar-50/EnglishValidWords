package MatchValidWords;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DictionaryEnglishWordTest.class);

        // Print failed test message
        for (Failure failures : result.getFailures()) {
            System.out.println(failures.toString());
        }

        // Display success test message
        System.out.println("Successful: " + result.wasSuccessful() + "\nExecuted tests " + result.getRunCount());
 
    }
}  
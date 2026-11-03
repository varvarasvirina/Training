package com.jpd.test.controller;

import com.jpd.test.model.Test;
import com.jpd.test.util.Database;
import com.jpd.test.view.ConsoleView;
import java.util.List;

/**
 * Handles business logic and data retrieval for the Student role.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class StudentController {
    private final ConsoleView view;

    /**
     * Constructs a {@code StudentController} with the specified view dependency.
     *
     * @param view the {@code ConsoleView} instance for displaying student-specific data
     */
    public StudentController(ConsoleView view) {
        this.view = view;
    }

    /**
     * Executes the requested action for the student role.
     *
     * @param action the string identifier of the action to perform
     */
    public void execute(String action) {
        if ("1".equals(action)) {
            List<Test> allTests = Database.tests;
            view.showTestsAndCounts(allTests);
        } else {
            view.printError();
        }
    }
}
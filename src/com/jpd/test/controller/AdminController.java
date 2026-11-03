package com.jpd.test.controller;

import com.jpd.test.model.User;
import com.jpd.test.util.Database;
import com.jpd.test.view.ConsoleView;
import java.util.List;

/**
 * Handles business logic and data retrieval for the Administrator role.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class AdminController {
    private final ConsoleView view;

    /**
     * Constructs an {@code AdminController} with the specified view dependency.
     *
     * @param view the {@code ConsoleView} instance for displaying admin-specific data
     */
    public AdminController(ConsoleView view) {
        this.view = view;
    }

    /**
     * Executes the requested action for the administrator role.
     *
     * @param action the string identifier of the action to perform
     */
    public void execute(String action) {
        if ("1".equals(action)) {
            List<User> allUsers = Database.users;
            view.showUsers(allUsers);
        } else {
            view.printError();
        }
    }
}
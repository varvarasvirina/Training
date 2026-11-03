package com.jpd.test;

import com.jpd.test.controller.AppController;
import com.jpd.test.view.ConsoleView;

/**
 * Main entry point for the Remote Testing System application (SE-2).
 * Initializes the View and Controller components and starts the application lifecycle.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class Main {
    /**
     * Starts the application execution.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();

        AppController controller = new AppController(view);

        controller.run();
    }
}
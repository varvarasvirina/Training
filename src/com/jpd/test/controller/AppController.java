package com.jpd.test.controller;

import com.jpd.test.model.Role;
import com.jpd.test.view.ConsoleView;

/**
 * Main application coordinator.
 * Manages the execution loop, role switching, and delegates
 * role-specific actions to dedicated controllers.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class AppController {
    private final ConsoleView view;
    private final StudentController studentController;
    private final TeacherController teacherController;
    private final AdminController adminController;

    /**
     * Constructs the {@code AppController} and initializes role-specific controllers.
     *
     * @param view the {@code ConsoleView} instance used for user interaction
     */
    public AppController(ConsoleView view) {
        this.view = view;
        this.studentController = new StudentController(view);
        this.teacherController = new TeacherController(view);
        this.adminController = new AdminController(view);
    }

    /**
     * Executes the main application loop with support for seamless role switching.
     */
    public void run() {
        view.printHeader();
        boolean appRunning = true;

        while (appRunning) {

            Role currentRole = view.promptRole();
            boolean roleSessionActive = true;

            while (roleSessionActive) {
                switch (currentRole) {
                    case STUDENT -> view.printStudentMenu();
                    case TEACHER -> view.printTeacherMenu();
                    case ADMIN -> view.printAdminMenu();
                }

                String action = view.readAction();

                switch (action) {
                    case "1" -> handleRoleAction(currentRole);
                    case "2" -> {
                        System.out.println("\nВозврат к выбору роли");
                        roleSessionActive = false;
                    }
                    case "0" -> {
                        appRunning = false;
                        roleSessionActive = false;
                        view.printExit();
                    }
                    default -> view.printError();
                }
            }
        }
    }

    /**
     * Delegates the action execution to the appropriate role-specific controller.
     *
     * @param role the current {@code Role} of the logged-in user
     */
    private void handleRoleAction(Role role) {
        switch (role) {
            case STUDENT -> studentController.execute("1");
            case TEACHER -> teacherController.execute("1");
            case ADMIN -> adminController.execute("1");
        }
    }
}
package com.jpd.test.view;

import com.jpd.test.model.*;
import java.util.List;

/**
 * Main View facade for the application.
 * Delegates role-specific UI tasks to specialized components,
 * providing a clean and unified interface for the Controller.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class ConsoleView {
    private final ConsoleInput input;
    private final StudentView studentView;
    private final TeacherView teacherView;
    private final AdminView adminView;

    /**
     * Constructs the {@code ConsoleView} facade and initializes its role-specific components.
     */
    public ConsoleView() {
        this.input = new ConsoleInput();
        this.studentView = new StudentView();
        this.teacherView = new TeacherView();
        this.adminView = new AdminView();
    }

    /**
     * Prints the main application header to the console.
     */
    public void printHeader() {
        System.out.println("=== Система дистанционного тестирования ===");
    }

    /**
     * Prints a farewell message upon application termination.
     */
    public void printExit() {
        System.out.println("Выход из системы.");
    }

    /**
     * Prints an error message for invalid menu selections.
     */
    public void printError() {
        System.out.println("Неверный пункт меню.");
    }

    /**
     * Prompts the user to select a system role and returns the chosen role.
     *
     * @return the selected {@code Role} (defaults to STUDENT on invalid input)
     */
    public Role promptRole() {
        return input.promptRole();
    }

    /**
     * Reads the user's menu action choice from the console.
     *
     * @return the trimmed string input representing the chosen action
     */
    public String readAction() {
        return input.readAction();
    }

    /**
     * Prompts the user to enter a group ID from the console.
     *
     * @return the entered group ID (defaults to 1 if the input is invalid)
     */
    public int promptGroupId() { return input.promptGroupId(); }

    /**
     * Prints the interactive menu options available to a Student.
     */
    public void printStudentMenu() {
        studentView.printMenu();
    }

    /**
     * Prints the interactive menu options available to a Teacher.
     */
    public void printTeacherMenu() {
        teacherView.printMenu();
    }

    /**
     * Prints the interactive menu options available to an Administrator.
     */
    public void printAdminMenu() {
        adminView.printMenu();
    }


    /**
     * Displays a formatted list of available tests and their question counts.
     *
     * @param tests the list of {@code Test} objects to display
     */
    public void showTestsAndCounts(List<Test> tests) {
        studentView.showTests(tests);
    }

    /**
     * Displays the teacher's dashboard, showing their tests and associated student results.
     *
     * @param myTests    the list of tests created by the current teacher
     * @param allResults the list of all test results in the system
     * @param allUsers   the list of all users (used to resolve student names)
     */
    public void showTeacherDashboard(List<Test> myTests, List<TestResult> allResults, List<User> allUsers) {
        teacherView.showDashboard(myTests, allResults, allUsers);
    }

    /**
     * Displays a formatted list of all system users and their roles.
     *
     * @param users the list of {@code User} objects to display
     */
    public void showUsers(List<User> users) {
        adminView.showUsers(users);
    }

    /**
     * Displays a formatted table of students and their academic ratings for a specific group.
     *
     * @param groupName the name of the group being displayed
     * @param ratings   the list of {@code StudentRating} objects sorted by rating in descending order
     */
    public void printGroupRating(String groupName, List<StudentRating> ratings) {
        teacherView.printGroupRating(groupName, ratings);
    }

    /**
     * Displays a list of available groups for the teacher to choose from.
     *
     * @param groups the list of all groups in the database
     */
    public void showGroupList(List<Group> groups) {
        teacherView.showGroupList(groups);
    }

}
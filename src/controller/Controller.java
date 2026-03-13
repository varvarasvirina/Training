package controller;

import model.Data;
import model.User;
import model.Test;
import model.Result;
import view.Console;

import java.util.List;
import java.util.Optional;

/**
 * Main controller that handles application logic.
 * Controller ONLY knows about the Model, NOT the View.
 * It processes user actions and updates the model.
 *
 * @version 1.10 12 March 2026
 * @author Varvara Svirina
 */
public class Controller {

    /** Reference to the model */
    private final Data model;

    /** Current application state */
    private Console.AppState currentState = Console.AppState.USER_SELECTION;

    /**
     * Constructs a Controller with the given model.
     * Note: Controller does NOT depend on View!
     *
     * @param model the data model
     */
    public Controller(Data model) {
        this.model = model;
    }

    /**
     * Handles user login action.
     * This is called by the view when user selects an ID.
     *
     * @param userId the ID of the user to login
     * @return true if login was successful
     */
    public boolean handleUserLogin(int userId) {
        Optional<User> userOpt = model.findUserById(userId);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            model.setCurrentUser(user);

            // Set appropriate state based on user role
            switch (user.getRole()) {
                case STUDENT:
                    currentState = Console.AppState.STUDENT_MENU;
                    break;
                case TEACHER:
                    currentState = Console.AppState.TEACHER_MENU;
                    break;
                case ADMIN:
                    currentState = Console.AppState.ADMIN_MENU;
                    break;
            }
            return true;
        } else {
            // User not found
            System.out.println("Пользователь с ID " + userId + " не найден.");
            return false;
        }
    }

    /**
     * Handles menu choice action.
     * This is called by the view when user makes a menu selection.
     *
     * @param choice the menu choice selected by user
     */
    public void handleMenuChoice(int choice) {
        User currentUser = model.getCurrentUser();
        if (currentUser == null) {
            return;
        }

        switch (currentUser.getRole()) {
            case STUDENT:
                handleStudentChoice(choice);
                break;
            case TEACHER:
                handleTeacherChoice(choice);
                break;
            case ADMIN:
                handleAdminChoice(choice);
                break;
        }
    }

    /**
     * Handles student menu choices.
     *
     * @param choice the choice selected
     */
    private void handleStudentChoice(int choice) {
        switch (choice) {
            case 1:
                // Student wants to see tests
                System.out.println("\nЗапрошен список тестов");
                break;
            case 0:
                // Logout
                model.logout();
                currentState = Console.AppState.USER_SELECTION;
                break;
            default:
                System.out.println("Неверный выбор!");
        }
    }

    /**
     * Handles teacher menu choices.
     *
     * @param choice the choice selected
     */
    private void handleTeacherChoice(int choice) {
        int teacherId = model.getCurrentUser().getId();
        switch (choice) {
            case 1:
                // Teacher wants to see their tests
                List<Test> myTests = model.getTestsByAuthor(teacherId);
                System.out.println("\nМои тесты");
                if (myTests.isEmpty()) {
                    System.out.println("У вас нет созданных тестов.");
                } else {
                    for (Test test : myTests) {
                        System.out.println(test);
                    }
                }
                System.out.println();
                break;
            case 2:
                // Teacher wants to see results for their tests
                List<Test> tests = model.getTestsByAuthor(teacherId);
                if (tests.isEmpty()) {
                    System.out.println("У вас нет тестов для просмотра результатов.");
                } else {
                    for (Test test : tests) {
                        System.out.println("\nРезультаты по тесту: " + test.getTitle());
                        List<Result> results = model.getResultsByTest(test.getNumber());
                        if (results.isEmpty()) {
                            System.out.println("Нет результатов.");
                        } else {
                            for (Result result : results) {
                                System.out.println(result);
                            }
                        }
                    }
                }
                System.out.println();
                break;

                    case 0:
                model.logout();
                currentState = Console.AppState.USER_SELECTION;
                break;
            default:
                System.out.println("Неверный выбор!");
        }
    }

    /**
     * Handles admin menu choices.
     *
     * @param choice the choice selected
     */
    private void handleAdminChoice(int choice) {
        switch (choice) {
            case 1:
                // Admin wants to see all users
                System.out.println("\nЗапрошен список пользователей");
                break;
            case 0:
                model.logout();
                currentState = Console.AppState.USER_SELECTION;
                break;
            default:
                System.out.println("Неверный выбор!");
        }
    }

    /**
     * Handles application exit.
     */
    public void handleExit() {
        System.out.println("Выход из программы...");
    }

    /**
     * Returns the current application state.
     *
     * @return the current state
     */
    public Console.AppState getCurrentState() {
        return currentState;
    }

    /**
     * Gets the data model.
     *
     * @return the model
     */
    public Data getModel() {
        return model;
    }
}
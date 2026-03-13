package view;

import model.Data;
import model.Result;
import model.Test;
import model.User;

import java.util.List;
import java.util.Scanner;

/**
 * Console implementation of the view.
 * Observes the model and renders changes automatically.
 * Also handles user input and passes it to the controller via callbacks.
 *
 * @version 1.10 12 March 2026
 * @author Varvara Svirina
 */
public class Console implements Data.DataObserver {

    /** Reference to the model to observe */
    private final Data model;

    /** Scanner for reading user input */
    private final Scanner scanner = new Scanner(System.in);

    /** Callback for user login action */
    private UserAction userLoginAction;

    /** Callback for menu choice action */
    private MenuChoiceAction menuChoiceAction;

    /** Callback for exit action */
    private ExitAction exitAction;

    /** Current application state */
    private AppState currentState = AppState.USER_SELECTION;

    /**
     * Functional interface for handling user login.
     */
    @FunctionalInterface
    public interface UserAction {
        /**
         * Called when a user is selected.
         * @param userId the ID of the selected user
         */
        void onUserSelected(int userId);
    }

    /**
     * Functional interface for handling menu choices.
     */
    @FunctionalInterface
    public interface MenuChoiceAction {
        /**
         * Called when a menu choice is made.
         * @param choice the selected menu option
         */
        void onMenuChoice(int choice);
    }

    /**
     * Functional interface for handling exit.
     */
    @FunctionalInterface
    public interface ExitAction {
        /**
         * Called when the user exits the application.
         */
        void onExit();
    }

    /**
     * Enumeration of application states.
     */
    public enum AppState {
        USER_SELECTION,
        STUDENT_MENU,
        TEACHER_MENU,
        ADMIN_MENU
    }

    /**
     * Constructs a Console view that observes the given model.
     *
     * @param model the data model to observe
     */
    public Console(Data model) {
        this.model = model;
        model.addObserver(this);
    }

    /**
     * Sets the callback for user login.
     *
     * @param action the action to execute when user is selected
     */
    public void setUserLoginAction(UserAction action) {
        this.userLoginAction = action;
    }

    /**
     * Sets the callback for menu choices.
     *
     * @param action the action to execute on menu choice
     */
    public void setMenuChoiceAction(MenuChoiceAction action) {
        this.menuChoiceAction = action;
    }

    /**
     * Sets the callback for exit.
     *
     * @param action the action to execute on exit
     */
    public void setExitAction(ExitAction action) {
        this.exitAction = action;
    }

    /**
     * Sets the current application state.
     *
     * @param state the new state
     */
    public void setCurrentState(AppState state) {
        this.currentState = state;
        render();
    }


    /**
     * Reads an integer from user input with validation.
     *
     * @return the integer value entered by the user
     */
    private int readIntegerInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Ошибка: введите число: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        return value;
    }

    /**
     * Handles user input based on current state.
     */
    public void handleInput() {
        switch (currentState) {
            case USER_SELECTION:
                handleUserSelection();
                break;
            case STUDENT_MENU:
            case TEACHER_MENU:
            case ADMIN_MENU:
                handleMenuInput();
                break;
        }
    }

    /**
     * Handles user selection input.
     */
    private void handleUserSelection() {
        int userId = readIntegerInput();

        if (userId == 0) {
            if (exitAction != null) {
                exitAction.onExit();
            }
        } else {
            if (userLoginAction != null) {
                userLoginAction.onUserSelected(userId);
            }
        }
    }

    /**
     * Handles menu input.
     */
    private void handleMenuInput() {
        int choice = readIntegerInput();
        if (menuChoiceAction != null) {
            menuChoiceAction.onMenuChoice(choice);
        }
    }

    /**
     * Called when the model changes.
     * Automatically re-renders the view.
     */
    @Override
    public void onDataChanged() {
        // View will re-render when needed
    }

    /**
     * Renders the current state based on the model.
     */
    public void render() {
        switch (currentState) {
            case USER_SELECTION:
                renderUserSelection();
                break;
            case STUDENT_MENU:
                renderStudentMenu();
                break;
            case TEACHER_MENU:
                renderTeacherMenu();
                break;
            case ADMIN_MENU:
                renderAdminMenu();
                break;
        }
    }

    /**
     * Renders the user selection screen.
     */
    private void renderUserSelection() {
        System.out.println("\nСистема дистанционного тестирования:");
        System.out.println("\nСписок пользователей:");

        List<User> users = model.getUsers();
        for (User user : users) {
            System.out.println(user);
        }

        System.out.println("0 Выйти из программы");
        System.out.print("\nВведите ID пользователя: ");
    }

    /**
     * Renders the student menu.
     */
    private void renderStudentMenu() {
        User currentUser = model.getCurrentUser();
        if (currentUser != null) {
            System.out.println("\nСтудент [" + currentUser.getName() + "]");
            System.out.println("1. Просмотреть список тестов");
            System.out.println("0. Вернуться к выбору пользователя");
            System.out.print("Выберите действие: ");
        }
    }

    /**
     * Renders the teacher menu.
     */
    private void renderTeacherMenu() {
        User currentUser = model.getCurrentUser();
        if (currentUser != null) {
            System.out.println("\nПреподаватель [" + currentUser.getName() + "]");
            System.out.println("1. Мои тесты");
            System.out.println("2. Результаты по моим тестам");
            System.out.println("0. Вернуться к выбору пользователя");
            System.out.print("Выберите действие: ");
        }
    }

    /**
     * Renders the admin menu.
     */
    private void renderAdminMenu() {
        User currentUser = model.getCurrentUser();
        if (currentUser != null) {
            System.out.println("\nАдминистратор [" + currentUser.getName() + "]");
            System.out.println("1. Список всех пользователей");
            System.out.println("0. Вернуться к выбору пользователя");
            System.out.print("Выберите действие: ");
        }
    }

    /**
     * Displays the list of tests.
     *
     * @param tests the list of tests to display
     */
    public void displayTests(List<Test> tests) {
        System.out.println("\nДоступные тесты:");
        if (tests.isEmpty()) {
            System.out.println("Нет доступных тестов.");
        } else {
            for (Test test : tests) {
                System.out.println(test);
            }
        }
        System.out.println();
    }

    /**
     * Closes the view and releases resources.
     */
    public void close() {
        scanner.close();
    }
}
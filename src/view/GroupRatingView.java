package view;

import controller.Controller;
import model.Group;
import model.StudentRating;

import java.util.List;
import java.util.Scanner;


/**
 * A View for console interaction.
 * Delegates all business logic to the controller. Does not change the model directly.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class GroupRatingView {
    private final Controller controller;
    private final Scanner scanner;
    private boolean isRunning;

    /**
     * Constructor a {@code GroupRatingView} with the controller and scanner.
     *
     * @param controller rating calculation controller
     * @param scanner    the console input scanner
     */
    public GroupRatingView(Controller controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
        this.isRunning = true;
    }

    /** Displays the welcome screen of the application. */
    public void displayWelcomeScreen() {
        System.out.println("СИСТЕМА РАСЧЁТА РЕЙТИНГОВ ГРУПП\n");
    }

    /** Displays the main menu with available actions. */
    public void displayMainMenu() {
        System.out.println("\nГлавное меню:");
        System.out.println("  [1] Показать все группы");
        System.out.println("  [2] Показать всех студентов");
        System.out.println("  [3] Показать рейтинг группы");
        System.out.println("  [0] Выход");
        System.out.print("\nВыберите действие: ");
    }

    /** Handles user selection from the main menu. */
    public void handleMenuSelection() {
        try {
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    handleShowAllGroups();
                    break;
                case "2":
                    handleShowAllStudents();
                    break;
                case "3":
                    handleShowGroupRating();
                    break;
                case "0":
                    handleExit();
                    break;
                default:
                    displayInvalidOptionMessage();
            }
        } catch (Exception e) {
            displayErrorMessage("Ошибка обработки ввода");
        }
    }

    /** Processes a request to display all groups. */
    private void handleShowAllGroups() {
        System.out.println("\nСписок всех групп:");
        System.out.printf("%-5s | %s%n", "ID", "Название");
        for (Group g : controller.getAvailableGroups()) {
            System.out.printf("%-5d | %s%n", g.getId(), g.getName());
        }
    }

    /** Processes a request to display all students. */
    private void handleShowAllStudents() {
        System.out.println("\nСписок всех студентов:");
        System.out.printf("%-5s | %-15s | %-5s | %s%n", "ID", "Имя", "Группа ID", "Группа");
        for (Group g : controller.getAvailableGroups()) {
            for (StudentRating sr : controller.getGroupRatingsDescending(g.getId())) {
                System.out.printf("%-5d | %-15s | %-5d | %s%n",
                        sr.getStudent().getId(),
                        sr.getStudent().getName(),
                        sr.getStudent().getGroupId(),
                        g.getName());
            }
        }
    }

    /** Displays a list of available groups to select. */
    public void handleShowGroupRating() {
        System.out.println("\nДоступные группы:");
        List<Group> groups = controller.getAvailableGroups();
        for (Group g : groups) {
            System.out.printf("  [%d] %s%n", g.getId(), g.getName());
        }

        System.out.print("\nВведите ID группы: ");
        try {
            int groupId = Integer.parseInt(scanner.nextLine().trim());
            List<StudentRating> ratings = controller.getGroupRatingsDescending(groupId);
            if (ratings.isEmpty()) {
                displayEmptyGroupMessage();
                return;
            }
            renderRatingResults(ratings);
        } catch (NumberFormatException e) {
            displayInvalidInputMessage();
        }
    }

    /** Processes the request to exit the application. */
    private void handleExit() {
        System.out.println("\nДо свидания!");
        isRunning = false;
    }

    /** Displays a rating table. */
    public void renderRatingResults(List<StudentRating> ratings) {
        System.out.println("\nРейтинги группы ID: " + ratings.get(0).getStudent().getGroupId());
        System.out.printf("%-15s | %-15s%n", "Студент", "Рейтинг (0-10)");

        for (StudentRating rating : ratings) {
            System.out.printf("%-15s | %-15s%n",
                    rating.getStudent().getName(),
                    rating.getRating()
                    );
        }

    }

    /** Displays a message about an incorrect selection of a menu item. */
    private void displayInvalidOptionMessage() {
        System.out.println("Неверный пункт меню. Выберите число от 0 до 3.");
    }

    /** Displays an input error message. */
    private void displayInvalidInputMessage() {
        System.out.println("Неверный ввод. Пожалуйста, введите числовой ID группы.");
    }

    /** Displays a message if the group is empty or not found. */
    private void displayEmptyGroupMessage() {
        System.out.println("В выбранной группе нет студентов или она не существует.");
    }

    /** Displays a general error message. */
    private void displayErrorMessage(String message) {
        System.out.println(message);
    }

    /** Returns the flag for continued operation of the application. */
    public boolean isRunning() {
        return isRunning;
    }

    /** Closes the scanner's resources. */
    public void closeScanner() {
        scanner.close();
    }
}
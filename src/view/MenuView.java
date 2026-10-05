package view;

import java.util.Scanner;

/**
 * View component for the main navigation menu.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class MenuView {
    private final Scanner scanner;

    /**
     * Constructs a {@code MenuView}.
     *
     * @param scanner the console input scanner
     */
    public MenuView(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Displays the main menu and returns the user's choice.
     *
     * @return the selected menu option integer, or {@code -1} if the input is invalid
     */
    public int displayMainMenu() {
        System.out.println("\nГлавное меню:");
        System.out.println(" 1) Список всех групп");
        System.out.println(" 2) Список всех студентов");
        System.out.println(" 3) Показать рейтинг группы");
        System.out.println(" 4) Пройти тест");
        System.out.println(" 0) Выход из системы");
        System.out.print("Выберите действие: ");

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
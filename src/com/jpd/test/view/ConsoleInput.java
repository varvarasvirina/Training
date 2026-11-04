package com.jpd.test.view;

import com.jpd.test.model.Role;
import java.util.Scanner;

/**
 * Handles all console input operations.
 * Encapsulates the Scanner and user prompt logic.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class ConsoleInput {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to select a system role and returns the chosen role.
     *
     * @return the selected {@code Role} (defaults to STUDENT on invalid input)
     */
    public Role promptRole() {
        System.out.println("\nВыберите роль для входа:");
        System.out.println("1 - Студент | 2 - Преподаватель | 3 - Администратор");
        System.out.print("Ваш выбор: ");
        String input = scanner.nextLine().trim();
        return switch (input) {
            case "1" -> Role.STUDENT;
            case "2" -> Role.TEACHER;
            case "3" -> Role.ADMIN;
            default -> {
                System.out.println("Некорректно. Запуск от имени Студента.");
                yield Role.STUDENT;
            }
        };
    }

    /**
     * Reads the user's menu action choice from the console.
     *
     * @return the trimmed string input representing the chosen action
     */
    public String readAction() {
        System.out.print("Действие: ");
        return scanner.nextLine().trim();
    }

    /**
     * Prompts the user to enter a group ID with error handling.
     */
    public int promptGroupId() {
        System.out.print("Введите ID группы (например, 1 или 2): ");
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Используется группа по умолчанию (ID: 1).");
            return 1;
        }
    }
}
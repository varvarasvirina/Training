package com.jpd.test.view;

import com.jpd.test.model.Test;
import java.util.List;

/**
 * Handles all console output operations specific to the Student role.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class StudentView {

    /**
     * Prints the interactive menu options available to a Student.
     */
    public void printMenu() {
        System.out.println("\nМеню Студента: ");
        System.out.println("1. Сменить пользователя");
        System.out.println("2. Просмотреть список тестов");
        System.out.println("0. Выход");
    }

    /**
     * Displays a formatted list of available tests and their question counts.
     *
     * @param tests the list of {@code Test} objects to display
     */
    public void showTests(List<Test> tests) {
        System.out.println("\nДоступные тесты:");
        for (Test t : tests) {
            System.out.printf(" [%d] %s | Вопросов: %d%n", t.getId(), t.getTitle(), t.getQuestionCount());
        }
    }
}
package com.jpd.test.view;

import com.jpd.test.model.Test;
import com.jpd.test.model.User;
import com.jpd.test.model.TestResult;
import java.util.List;

/**
 * Handles all console output operations specific to the Teacher role.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class TeacherView {

    /**
     * Prints the interactive menu options available to a Teacher.
     */
    public void printMenu() {
        System.out.println("\nМеню Преподавателя:");
        System.out.println("1. Просмотреть свои тесты и результаты студентов");
        System.out.println("2. Сменить пользователя");
        System.out.println("0. Выход");
    }

    /**
     * Displays the teacher's dashboard, showing their tests and associated student results.
     *
     * @param myTests    the list of tests created by the current teacher
     * @param allResults the list of all test results in the system
     * @param allUsers   the list of all users (used to resolve student names)
     */
    public void showDashboard(List<Test> myTests, List<TestResult> allResults, List<User> allUsers) {
        System.out.println("\nВаши тесты и результаты:");
        if (myTests.isEmpty()) {
            System.out.println("У вас нет созданных тестов.");
            return;
        }
        for (Test t : myTests) {
            System.out.printf(" Тест: %s (ID: %d)%n", t.getTitle(), t.getId());
            boolean hasRes = false;
            for (TestResult res : allResults) {
                if (res.getTestId() == t.getId()) {
                    String sName = allUsers.stream()
                            .filter(u -> u.getId() == res.getStudentId())
                            .map(User::getName)
                            .findFirst()
                            .orElse("Неизвестный");
                    System.out.printf("   %s: %.1f баллов%n", sName, res.getScore());
                    hasRes = true;
                }
            }
            if (!hasRes) {
                System.out.println("   Нет результатов");
            }
        }
    }
}
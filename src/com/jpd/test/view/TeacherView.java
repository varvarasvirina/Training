package com.jpd.test.view;

import com.jpd.test.model.*;
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
        System.out.println("1. Сменить пользователя");
        System.out.println("2. Просмотреть свои тесты и результаты студентов");
        System.out.println("3. Рассчитать рейтинг группы");

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

    /**
     * Displays a list of available groups for selection.
     *
     * @param groups the list of all groups
     */
    public void showGroupList(List<Group> groups) {
        System.out.println("\n Доступные группы:");
        System.out.printf("%-5s | %-20s%n", "ID", "Название группы");
        for (Group g : groups) {
            System.out.printf("%-5d | %-20s%n", g.getId(), g.getName());
        }
    }

    /**
     * Renders a formatted table of students and their ratings.
     *
     * @param groupName the name of the group being displayed
     * @param ratings   the list of {@code StudentRating} objects sorted by rating
     */
    public void printGroupRating(String groupName, List<StudentRating> ratings) {
        System.out.println("\nРейтинг группы: " + groupName);
        System.out.println("--------------------------------------------------");
        System.out.printf("%-20s | %s%n", "Студент", "Рейтинг (0-10)");
        System.out.println("--------------------------------------------------");
        for (StudentRating sr : ratings) {
            System.out.printf("%-20s | %.2f%n", sr.getStudent().getName(), sr.getRating());
        }
        System.out.println("--------------------------------------------------");
    }
}
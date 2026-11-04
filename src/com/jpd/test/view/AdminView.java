package com.jpd.test.view;

import com.jpd.test.model.User;
import java.util.List;

/**
 * Handles all console output operations specific to the Administrator role.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class AdminView {

    /**
     * Prints the interactive menu options available to an Administrator.
     */
    public void printMenu() {
        System.out.println("\nМеню Администратора: ");
        System.out.println("1. Сменить пользователя");
        System.out.println("2. Просмотреть список пользователей");
        System.out.println("0. Выход");
    }

    /**
     * Displays a formatted list of all system users and their roles.
     *
     * @param users the list of {@code User} objects to display
     */
    public void showUsers(List<User> users) {
        System.out.println("\nСписок пользователей: ");
        for (User u : users) {
            System.out.printf("ID: %d | %s | Роль: %s%n", u.getId(), u.getName(), u.getRole());
        }
    }
}
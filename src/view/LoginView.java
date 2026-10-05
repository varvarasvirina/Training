package view;

import controller.AuthController;
import java.util.Scanner;

/**
 * View component responsible for the user login screen.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class LoginView {
    private final Scanner scanner;

    /**
     * Constructs a {@code LoginView}.
     *
     * @param scanner the console input scanner
     */
    public LoginView(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Displays the login prompt and processes user input.
     *
     * @param authController the controller handling authentication logic
     * @return {@code true} if login was successful, {@code false} otherwise
     */
    public boolean showLoginScreen(AuthController authController) {
        System.out.println("ВХОД В СИСТЕМУ");
        System.out.println("---------------------------------------------");
        System.out.print("Введите ваше имя для входа: ");

        String username = scanner.nextLine().trim();
        if (authController.login(username)) {
            System.out.println("Добро пожаловать, " + authController.getCurrentUser() + "!");
            return true;
        } else {
            System.out.println("Имя не может быть пустым!");
            return false;
        }
    }
}
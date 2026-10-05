import util.Database;
import controller.*;
import view.*;

import java.util.Scanner;

/**
 * The entry point to the application. Binds the MVC components and starts execution.
 *
 * @version 1.10 23 June 2026
 * @author Varvara Svirina
 */
public class Main {

    /**
     * The standard JVM input method. Delegates control to run Application().
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        runApplication();
    }

    /**
     * Initializes the model, controller, and view, then launches the UI.
     */
    public static void runApplication() {
        // Data
        Database db = new Database();

        // Controllers
        AuthController authCtrl = new AuthController();
        GroupController groupCtrl = new GroupController(db);
        StudentController studentCtrl = new StudentController(db);
        TestController testCtrl = new TestController(db);
        TestResultController resultCtrl = new TestResultController(db);
        RatingController ratingCtrl = new RatingController(db, testCtrl);
        TestExecutionController testExecCtrl = new TestExecutionController(db, ratingCtrl);

        // Views
        Scanner scanner = new Scanner(System.in);
        LoginView loginView = new LoginView(scanner);
        MenuView menuView = new MenuView(scanner);
        GroupListView groupListView = new GroupListView();
        StudentListView studentListView = new StudentListView();
        RatingTableView ratingTableView = new RatingTableView();

        // Application Flow
        while (!authCtrl.checkAuthStatus()) {
            loginView.showLoginScreen(authCtrl);
        }

        boolean isRunning = true;
        while (isRunning) {
            int choice = menuView.displayMainMenu();

            switch (choice) {
                case 1:
                    groupListView.renderList(groupCtrl.getAllGroups());
                    break;
                case 2:
                    studentListView.renderList(studentCtrl.getAllStudents(), groupCtrl.getAllGroups());
                    break;
                case 3:
                    handleShowRatings(groupCtrl, ratingCtrl, ratingTableView, scanner);
                    break;
                case 4:
                    handleTakeTest(studentCtrl, testExecCtrl, scanner);
                    break;
                case 0:
                    authCtrl.logout();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
        scanner.close();
    }

    /**
     * Handles the logic for displaying group ratings.
     *
     * @param gCtrl GroupController
     * @param rCtrl RatingController
     * @param rView RatingTableView
     * @param sc Scanner
     */
    private static void handleShowRatings(GroupController gCtrl, RatingController rCtrl, RatingTableView rView, Scanner sc) {
        System.out.println("\nДоступные группы:");
        gCtrl.getAllGroups().forEach(g -> System.out.printf("[%d] %s%n", g.getId(), g.getName()));

        System.out.print("Введите ID группы: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            var ratings = rCtrl.getGroupRatingsSortedDesc(id);
            rView.renderTable(ratings);
        } catch (Exception e) {
            System.out.println("Ошибка ввода ID.");
        }
    }
    /**
     * Handles the user interaction flow for taking a test.
     * Prompts the user to select a student, runs the predefined test,
     * and displays the completion status.
     *
     * @param sCtrl  the controller for accessing student data
     * @param teCtrl the controller responsible for executing the test logic
     * @param sc     the scanner for reading console input
     */

    private static void handleTakeTest(StudentController sCtrl, TestExecutionController teCtrl, Scanner sc) {
        System.out.println("\nВыберите студента:");
        sCtrl.getAllStudents().forEach(s -> System.out.printf("[%d] %s%n", s.getId(), s.getName()));

        System.out.print("ID студента: ");
        int studentId;
        try {
            studentId = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Неверный ID.");
            return;
        }

        System.out.println("\nЗапуск теста 'Основы Java' (ID: 1)...");
        var result = teCtrl.runTest(studentId, 1, sc);

        if (result != null) {
            System.out.println("Результат сохранен. Рейтинг студента обновлен.");
        }
    }
}
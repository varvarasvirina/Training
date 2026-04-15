import model.Database;
import controller.Controller;
import view.GroupRatingView;

import java.util.Scanner;

/**
 * The entry point to the application. Binds the MVC components and starts execution.
 *
 * @version 1.10 27 March 2026
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
        Database database = new Database();
        Controller controller = new Controller(database);
        Scanner scanner = new Scanner(System.in);
        GroupRatingView view = new GroupRatingView(controller, scanner);

        view.displayWelcomeScreen();

        while (view.isRunning()) {
            view.displayMainMenu();
            view.handleMenuSelection();
        }

        view.closeScanner();
    }
}
import controller.Controller;
import model.Data;
import view.Console;

/**
 * Main entry point for the Testing System application
 *
 * @version 1.10 12 March 2026
 * @author Varvara Svirina
 */
public class Main {

    /**
     * Main method that initializes and runs the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Model
        Data model = new Data();
        model.initializeData();

        // Initialize View
        Console view = new Console(model);

        // Initialize Controller
        Controller controller = new Controller(model);

        // Wire View callbacks to Controller actions
        view.setUserLoginAction(userId -> {
            boolean success = controller.handleUserLogin(userId);
            if (success) {
                view.setCurrentState(controller.getCurrentState());
                displayDataForCurrentState(view, controller);
            }
        });

        view.setMenuChoiceAction(choice -> {
            controller.handleMenuChoice(choice);
            displayDataForCurrentState(view, controller);

            int currentChoice = choice;
            if (currentChoice == 0) {
                view.setCurrentState(controller.getCurrentState());
            }
        });

        view.setExitAction(() -> {
            controller.handleExit();
            view.close();
            System.exit(0);
        });

        // 5. Set initial state and start
        view.setCurrentState(Console.AppState.USER_SELECTION);

        // 6. Main input loop
        while (true) {
            view.handleInput();
        }
    }

    /**
     * Displays data based on current state and user choice.
     *
     * @param view the console view
     * @param controller the controller
     */
    private static void displayDataForCurrentState(Console view, Controller controller) {
        Data model = controller.getModel();

        switch (controller.getCurrentState()) {
            case STUDENT_MENU:
                // Student can see all tests
                view.displayTests(model.getTests());
                break;
            case TEACHER_MENU:
                // Teacher can see their tests and results
                int teacherId = model.getCurrentUser().getId();
                view.displayTests(model.getTestsByAuthor(teacherId));
                break;
            case ADMIN_MENU:
                // Admin can see all users
                System.out.println("\nСписок всех пользователей:");
                model.getUsers().forEach(System.out::println);
                System.out.println();
                break;
            default:
                break;
        }
    }
}
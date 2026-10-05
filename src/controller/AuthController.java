package controller;

/**
 * Controller responsible for user authentication and session management.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class AuthController {
    private boolean isLoggedIn = false;
    private String currentUser = "";

    /**
     * Attempts to log in a user with the specified username.
     *
     * @param username the name of the user attempting to log in
     * @return {@code true} if login is successful, {@code false} otherwise
     */
    public boolean login(String username) {
        if (username != null && !username.trim().isEmpty()) {
            this.isLoggedIn = true;
            this.currentUser = username;
            return true;
        }
        return false;
    }

    /**
     * Logs out the current user and clears session data.
     */
    public void logout() {
        this.isLoggedIn = false;
        this.currentUser = "";
    }

    /**
     * Checks if a user is currently authenticated.
     * @return {@code true} if a user is logged in
     */
    public boolean checkAuthStatus() {
        return isLoggedIn;
    }

    /**
     * Retrieves the name of the currently logged-in user.
     * @return the current username, or empty string if not logged in
     */
    public String getCurrentUser() {
        return currentUser;
    }
}
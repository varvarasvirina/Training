package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Data repository class that manages all application data.
 * Implements Observable pattern to notify views of changes.
 *
 * @version 1.10 12 March 2026
 * @author Varvara Svirina
 */
public class Data {

    /** List of all users in the system */
    private List<User> users = new ArrayList<>();

    /** List of all tests in the system */
    private List<Test> tests = new ArrayList<>();

    /** List of all test results */
    private List<Result> results = new ArrayList<>();

    /** Currently logged-in user */
    private User currentUser = null;

    /** List of observers to notify on data changes */
    private List<DataObserver> observers = new ArrayList<>();

    /**
     * Interface for observers that want to be notified of data changes.
     */
    public interface DataObserver {
        /**
         * Called when data has changed.
         */
        void onDataChanged();
    }

    /**
     * Initializes the data repository with sample data.
     */
    public void initializeData() {
        // Users
        users.add(new User("Александрова Мария", Users.STUDENT, 1));
        users.add(new User("Иванов Михаил", Users.STUDENT, 2));
        users.add(new User("Смирнов Валерий", Users.TEACHER, 3));
        users.add(new User("Морозова Елена", Users.TEACHER, 4));
        users.add(new User("Администратор", Users.ADMIN, 5));

        // Tests
        tests.add(new Test(1, "ООП", 3, 10));
        tests.add(new Test(2, "База данных", 4, 8));
        tests.add(new Test(3, "Алгоритмы", 3, 12));

        // Results
        results.add(new Result(1, 1, 73));
        results.add(new Result(1, 2, 90));
        results.add(new Result(1, 3, 54));
        results.add(new Result(2, 1, 89));
        results.add(new Result(2, 2, 68));
        results.add(new Result(2, 3, 93));

        // Notify observers that data is initialized
        notifyObservers();
    }

    /**
     * Registers an observer to be notified of data changes.
     *
     * @param observer the observer to register
     */
    public void addObserver(DataObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Removes an observer.
     *
     * @param observer the observer to remove
     */
    public void removeObserver(DataObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers that data has changed.
     */
    private void notifyObservers() {
        for (DataObserver observer : new ArrayList<>(observers)) {
            observer.onDataChanged();
        }
    }

    /**
     * Sets the currently logged-in user and notifies observers.
     *
     * @param user the user to set as current
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        notifyObservers();
    }

    /**
     * Returns the currently logged-in user.
     *
     * @return the current user, or null if no user is logged in
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Returns an unmodifiable list of all users.
     *
     * @return list of users
     */
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    /**
     * Returns an unmodifiable list of all tests.
     *
     * @return list of tests
     */
    public List<Test> getTests() {
        return Collections.unmodifiableList(tests);
    }

    /**
     * Returns an unmodifiable list of all results.
     *
     * @return list of results
     */
    public List<Result> getResults() {
        return Collections.unmodifiableList(results);
    }

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user to find
     * @return an Optional containing the user if found
     */
    public Optional<User> findUserById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    /**
     * Retrieves all tests created by a specific author.
     *
     * @param authorId the ID of the author
     * @return list of tests created by the specified author
     */
    public List<Test> getTestsByAuthor(int authorId) {
        return tests.stream()
                .filter(t -> t.getAuthorId() == authorId)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all results for a specific test.
     *
     * @param testNumber the test number
     * @return list of results for the specified test
     */
    public List<Result> getResultsByTest(int testNumber) {
        return results.stream()
                .filter(r -> r.getTestNumber() == testNumber)
                .collect(Collectors.toList());
    }

    /**
     * Clears the current user and notifies observers.
     */
    public void logout() {
        this.currentUser = null;
        notifyObservers();
    }
}
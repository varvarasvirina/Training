package controller;

import util.Database;

/**
 * Controller for managing {@code TestResult} entities.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class TestResultController {
    private final Database database;

    /**
     * Constructs a {@code TestResultController}.
     *
     * @param database the data repository
     */
    public TestResultController(Database database) {
        this.database = database;
    }
}
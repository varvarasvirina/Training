package controller;

import model.*;
import model.Test;
import util.Database;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * Controller for managing {@code Test} entities.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class TestController {
    private final Database database;

    /**
     * Constructs a {@code TestController}.
     *
     * @param database the data repository
     */
    public TestController(Database database) {
        this.database = database;
    }

    /**
     * Retrieves all tests from the database.
     *
     * @return list of all {@code Test} objects
     */
    public List<Test> getAllTests() {
        return database.getTests();
    }

    /**
     * Retrieves a specific {@code Test} by its unique identifier.
     *
     * @param id the test identifier
     * @return the {@code Test} object if found, or {@code null} if not found
     */
    public Test getTestById(int id) {
        return database.getTestById(id);
    }
}
package controller;

import model.Student;
import util.Database;
import java.util.List;

/**
 * Controller for managing {@code Student} entities.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class StudentController {
    private final Database database;

    /**
     * Constructs a {@code StudentController}.
     *
     * @param database the data repository
     */
    public StudentController(Database database) {
        this.database = database;
    }

    /**
     * Retrieves all students from the database.
     *
     * @return list of all {@code Student} objects
     */
    public List<Student> getAllStudents() {
        return database.getStudents();
    }
}
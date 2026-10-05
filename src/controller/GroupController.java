package controller;

import model.Group;
import util.Database;
import java.util.List;

/**
 * Controller for managing {@code Group} entities.
 *
 * @version 1.10 23 June 2026
 * @author Varvara Svirina
 */
public class GroupController {
    private final Database database;

    /**
     * Constructs a {@code GroupController}.
     *
     * @param database the data repository
     */
    public GroupController(Database database) {
        this.database = database;
    }

    /**
     * Retrieves all groups from the database.
     *
     * @return list of all {@code Group} objects
     */
    public List<Group> getAllGroups() {
        return database.getGroups();
    }

    /**
     * Retrieves a specific {@code Group} by its unique identifier.
     *
     * @param id the group identifier
     * @return the {@code Group} object if found, or {@code null} if not found
     */
    public Group getGroupById(int id) {
        return database.getGroupById(id);
    }
}
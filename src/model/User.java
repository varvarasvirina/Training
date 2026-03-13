package model;

/**
 * Represents a user in the testing system.
 * Contains user information including ID, name, and role.
 *
 * @version 1.10 12 March 2026
 * @author Varvara Svirina
 */
public class User {

    /** Unique identifier for the user */
    private int id;

    /** Name of the user */
    private String name;

    /** Role of the user in the system */
    private Users role;

    /**
     * Constructs a new User with the specified details.
     *
     * @param name the name of the user
     * @param role the role of the user
     * @param id the unique identifier for the user
     */
    public User(String name, Users role, int id) {
        this.name = name;
        this.role = role;
        this.id = id;
    }

    /**
     * Returns the user's ID.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the user's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the user's role.
     *
     * @return the role
     */
    public Users getRole() {
        return role;
    }

    /**
     * Returns a string representation of the user.
     *
     * @return formatted string with user information
     */
    @Override
    public String toString() {
        return id + " " + name + " " + role;
    }
}
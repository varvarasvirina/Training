package model;

/**
 * Represents an academic group of students.
 * The class is fully encapsulated: fields are immutable, accessible only through getters.
 *
 * @version 1.10 27 March 2026
 * @author Varvara Svirina
 */
public class Group {
    private final int id;
    private final String name;

    /**
     * Constructor a {@code Group} with the specified ID and name.
     *
     * @param id the unique identifier of the group
     * @param name the name of the group
     */
    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /** Returns the group ID.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /** Returns the name of the group.
     *
     * @return name
     */
    public String getName() {
        return name;
    }
}
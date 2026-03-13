package model;

/**
 * Enumeration of user roles in the testing system.
 * Defines the available user types: Student, Teacher, and Admin.
 *
 * @version 1.10 12 March 2026
 * @author Varvara Svirina
 */
public enum Users {

    /** Student role */
    STUDENT("Студент"),

    /** Teacher role */
    TEACHER("Преподаватель"),

    /** Administrator role */
    ADMIN("Администратор");

    /** Display name of the role in Russian */
    private final String displayName;

    /**
     * Constructs a Users enum with a display name.
     *
     * @param displayName the display name for this role
     */
    Users(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of this role.
     *
     * @return the display name
     */
    @Override
    public String toString() {
        return displayName;
    }
}
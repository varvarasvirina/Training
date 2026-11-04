package com.jpd.test.model;

/**
 * Represents a user of the training center system.
 * The class is fully encapsulated: fields are immutable and accessible only through getters.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class User {
    private final int id;
    private final String name;
    private final int groupId;
    private final Role role;


    /**
     * Constructs a {@code User} with the specified parameters.
     *
     * @param id      the unique identifier of the user
     * @param name    the full name of the user
     * @param groupId    the student group, 0 if it’s a teacher or admin
     * @param role    the user role (STUDENT, TEACHER, ADMIN)
     */
    public User(int id, String name, int groupId,  Role role) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
        this.role = role;

    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return the user ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the full name of the user.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the role of the user in the system.
     *
     * @return the user role
     */
    public Role getRole() {
        return role;
    }

    /** Returns the ID of the student's group.
     *
     * @return student's
     */
    public int getGroupId() {
        return groupId;
    }
}
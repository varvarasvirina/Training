package model;

/**
 * Represents the student enrolled in the study group.
 * The class is fully encapsulated: fields are immutable, accessible only through getters.
 *
 * @version 1.10 27 March 2026
 * @author Varvara Svirina
 */
public class Student {
    private final int id;
    private final String name;
    private final int groupId;

    /**
     * Constructor a {@code Student} with the specified id, name, and groupID.
     *
     * @param id is a unique student ID
     * @param name full name of the student
     * @param groupId is the ID of the group the student is enrolled in
     */
    public Student(int id, String name, int groupId) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
    }

    /** Returns the unique identifier of the student.
     *
     * @return the student id
     */
    public int getId() {
        return id;
    }

    /** Returns the full name of the student.
     *
     * @return the student's name
     */
    public String getName() {
        return name;
    }

    /** Returns the ID of the student's group.
     *
     * @return student's
     */
    public int getGroupId() {
        return groupId;
    }
}
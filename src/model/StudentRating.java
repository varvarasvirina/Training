package model;

/**
 * DTO (Data Transfer Object) to store the student and his calculated rating.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class StudentRating {
    private final Student student;
    private final double rating;

    /**
     * Constructor a {@code StudentRating} with the student and rating.
     *
     * @param student Student
     * @param rating  the calculated rating is in the range 0.0..10.0
     */
    public StudentRating(Student student, double rating) {
        this.student = student;
        this.rating = rating;
    }

    /** Returns the information about the student.
     *
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /** Returns the rating of this student.
     *
     * @return the student's rating
     */
    public double getRating() {
        return rating;
    }
}
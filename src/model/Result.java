package model;

/**
 * Represents a test result.
 * Contains information about a student's score on a specific test.
 *
 * @version 1.10 12 March 2026
 * @author Varvara Svirina
 */
public class Result {

    /** ID of the student who took the test */
    private int studentId;

    /** Test number */
    private int testNumber;

    /** Score achieved by the student */
    private int score;

    /**
     * Constructs a new Result with the specified details.
     *
     * @param studentId the ID of the student
     * @param testNumber the test number
     * @param score the score achieved
     */
    public Result(int studentId, int testNumber, int score) {
        this.studentId = studentId;
        this.testNumber = testNumber;
        this.score = score;
    }

    /**
     * Returns the student's ID.
     *
     * @return the student ID
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Returns the test number.
     *
     * @return the test number
     */
    public int getTestNumber() {
        return testNumber;
    }

    /**
     * Returns the score.
     *
     * @return the score achieved
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns a string representation of the result.
     *
     * @return formatted string with result information
     */
    @Override
    public String toString() {
        return "Студент " + studentId + ", Тест " + testNumber + ", Баллы: " + score;
    }
}
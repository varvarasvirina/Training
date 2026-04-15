package model;

/**
 * Represents the test result of a particular student.
 * The class is fully encapsulated: fields are immutable, accessible only through getters.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class TestResult {
    private final int id;
    private final int studentId;
    private final int testId;
    private final double score;

    /**
     * Constructor a {@code TestResult} with the specified id, studentId, testId and score.
     *
     * @param id the ID of the result record
     * @param studentId the student ID
     * @param testId the test ID
     * @param score the score received
     */
    public TestResult(int id, int studentId, int testId, double score) {
        this.id = id;
        this.studentId = studentId;
        this.testId = testId;
        this.score = score;
    }

    /** Returns the unique identifier of this result record.
     *
     * @return the TestResult id
     */
    public int getId() {
        return id;
    }

    /** Returns the identifier of the student who took the test.
     *
     * @return the student id
     */
    public int getStudentId() {
        return studentId;
    }

    /** Returns the identifier of the test associated with this result.
     *
     * @return the test id
     */
    public int getTestId() {
        return testId;
    }

    /** Returns the score achieved by the student for this test.
     *
     * @return the achieved score
     */
    public double getScore() {
        return score;
    }
}
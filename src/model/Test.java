package model;

/**
 * Represents a learning test with the highest score.
 * The class is fully encapsulated: fields are immutable, accessible only through getters.
 *
 * @version 1.10 27 March 2026
 * @author Varvara Svirina
 */
public class Test {
    private final int id;
    private final String name;
    private final double maxScore;

    /**
     * Constructor a {@code Test} with the specified id, name and maxScore.
     *
     * @param id the unique identifier of the test
     * @param name the name of the test
     * @param maxScore maximum possible score
     */
    public Test(int id, String name, double maxScore) {
        this.id = id;
        this.name = name;
        this.maxScore = maxScore;
    }

    /** Returns the unique identifier of the test.
     *
     * @return the test id
     */
    public int getId() {
        return id;
    }

    /** Returns the full name of the test.
     *
     * @return the test name
     */
    public String getName() {
        return name;
    }

    /** Returns the max possible score of the test.
     *
     * @return the maximum achievable score
     */
    public double getMaxScore() {
        return maxScore;
    }
}
package model;

import java.util.List;

/**
 * Represents a learning test with the highest score.
 * The class is fully encapsulated: fields are immutable, accessible only through getters.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class Test {
    private final int id;
    private final String name;
    private final List<Question> questions;

    /**
     * Constructor a {@code Test} with the specified id, name and maxScore.
     *
     * @param id the unique identifier of the test
     * @param name the name of the test
     * @param questions maximum possible score
     */
    public Test(int id, String name,  List<Question> questions) {
        this.id = id;
        this.name = name;
        this.questions = questions;
    }

    /** Returns the unique identifier of the test.
     *
     * @return the test id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the test.
     *
     * @return the test name
     */
    public String getName() { return name; }

    /**
     * Returns the list of questions included in the test.
     *
     * @return the list of {@code Question} objects
     */
    public List<Question> getQuestions() { return questions; }

    /**
     * Calculates the maximum possible score for the test. Assumes 10 points per question.
     *
     * @return the maximum score as a double value
     */
    public double getMaxScore() {
        return questions.size() * 10.0;
    }
}
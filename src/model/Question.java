package model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a test question with multiple choice options. Supports multiple correct answers.
 *
 * @version 1.10 23 June 2026
 * @author Varvara Svirina
 */
public class Question {
    private final int id;
    private final String text;
    private final List<String> options;
    private final List<Integer> correctOptionIndices;

    /**
     * Constructs a new {@code Question} with the specified parameters.
     *
     * @param id                  the unique identifier of the question
     * @param text                the text of the question
     * @param options             the list of possible answer options
     * @param correctOptionIndices the list of indices (0-based) corresponding to the correct answers
     */
    public Question(int id, String text, List<String> options, List<Integer> correctOptionIndices) {
        this.id = id;
        this.text = text;
        this.options = new ArrayList<>(options);
        this.correctOptionIndices = new ArrayList<>(correctOptionIndices);
    }

    /**
     * Returns the issue ID.
     *
     * @return the id
     */
    public int getId() { return id; }

    /**
     * Returns the text of the question.
     *
     * @return the text
     */
    public String getText() { return text; }

    /**
     * Returns the list of possible responses.
     *
     * @return the options
     */
    public List<String> getOptions() { return options; }

    /**
     * Returns the list of indexes of correct answers.
     *
     * @return the correctOptionIndices
     */
    public List<Integer> getCorrectOptionIndices() { return correctOptionIndices; }

    /**
     * Checks if the user's selected indices match the correct ones exactly.Order does not matter.
     *
     * @param selectedIndices   the list of indexes selected by the user
     */
    public boolean isAnswerCorrect(List<Integer> selectedIndices) {
        if (selectedIndices.size() != correctOptionIndices.size()) return false;

        List<Integer> sortedSelected = new ArrayList<>(selectedIndices);
        List<Integer> sortedCorrect = new ArrayList<>(correctOptionIndices);

        sortedSelected.sort(Integer::compareTo);
        sortedCorrect.sort(Integer::compareTo);

        return sortedSelected.equals(sortedCorrect);
    }
}
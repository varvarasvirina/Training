package com.jpd.test.model;


/**
 * Represents a test question with multiple choice options. Supports multiple correct answers.
 *
 * @version 1.10 23 June 2026
 * @author Varvara Svirina
 */
public class Question {
    private final int id;
    private final String text;


    /**
     * Constructs a new {@code Question} with the specified parameters.
     *
     * @param id                   the unique identifier of the question
     * @param text                 the text of the question
     */
    public Question(int id, String text) {
        this.id = id;
        this.text = text;
    }

    /**
     * Returns the unique identifier of the question.
     *
     * @return the question ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the text of the question.
     *
     * @return the question text
     */
    public String getText() {
        return text;
    }
}
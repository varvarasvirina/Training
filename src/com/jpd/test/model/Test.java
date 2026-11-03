package com.jpd.test.model;

import java.util.List;

/**
 * Represents a test created by a teacher.
 * The class is fully encapsulated: fields are immutable and accessible only through getters.
 *
 * @version 1.10 23 June 2026
 * @author Varvara Svirina
 */
public class Test {
    private final int id;
    private final String title;
    private final int teacherId;
    private final List<Question> questions;

    /**
     * Constructs a {@code Test} with the specified parameters.
     *
     * @param id        the unique identifier of the test
     * @param title     the title or name of the test
     * @param teacherId the unique identifier of the teacher who created the test
     * @param questions the list of questions included in the test
     */
    public Test(int id, String title, int teacherId, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.teacherId = teacherId;
        this.questions = questions;
    }

    /**
     * Returns the unique identifier of the test.
     *
     * @return the test ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the title or name of the test.
     *
     * @return the test title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the unique identifier of the teacher who created this test.
     *
     * @return the teacher ID
     */
    public int getTeacherId() {
        return teacherId;
    }

    /**
     * Returns the total number of questions in this test.
     *
     * @return the count of questions, or 0 if the list is null
     */
    public int getQuestionCount() {
        return questions != null ? questions.size() : 0;
    }
}
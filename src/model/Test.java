package model;

/**
 * Represents a test in the system.
 * Contains test information including number, title, author, and question count.
 *
 * @version 1.10 12 March 2026
 * @author Varvara Svirina
 */
public class Test {

    /** Test number identifier */
    private int number;

    /** Title of the test */
    private String title;

    /** ID of the teacher who created this test */
    private int authorId;

    /** Number of questions in the test */
    private int questionCount;

    /**
     * Constructs a new Test with the specified details.
     *
     * @param number the test number
     * @param title the test title
     * @param authorId the ID of the test author
     * @param questionCount the number of questions in the test
     */
    public Test(int number, String title, int authorId, int questionCount) {
        this.number = number;
        this.title = title;
        this.authorId = authorId;
        this.questionCount = questionCount;
    }

    /**
     * Returns the test number.
     *
     * @return the test number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the test title.
     *
     * @return the test title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author's ID.
     *
     * @return the author ID
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * Returns the number of questions.
     *
     * @return the question count
     */
    public int getQuestionCount() {
        return questionCount;
    }

    /**
     * Returns a string representation of the test.
     *
     * @return formatted string with test information
     */
    @Override
    public String toString() {
        return number + ". " + title + ", Количество вопросов: " + questionCount;
    }
}
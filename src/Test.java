public class Test {
    public int number;
    public String title;
    public int authorId;
    public int questionCount;

    public Test(int number, String title, int authorId, int questionCount) {
        this.number = number;
        this.title = title;
        this.authorId = authorId;
        this.questionCount = questionCount;
    }

    public String toString() {
        return number + ". " + title + ", Количество вопросов:" + questionCount;
    }
}


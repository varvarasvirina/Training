public class Result {
    public int studentId;
    public int testnumber;
    public int score;

    public Result(int studentId, int testnumber, int score) {
        this.studentId = studentId;
        this.testnumber = testnumber;
        this.score = score;
    }


    public String toString() {
        return studentId + ". " + testnumber + " Набрано баллов: " + score;
    }
}
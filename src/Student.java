import java.util.List;
public class Student
{
    public static void showTests() {
        System.out.println("\n     Доступные тесты     ");
        List<Test> tests = Data.tests;
        if (tests.isEmpty()) {
            System.out.println("Нет доступных тестов.");
        } else {
            for (Test test : tests) {
                System.out.println(test);
            }
        }
    }
}

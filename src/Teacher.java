import java.util.List;

public class Teacher {
    public static void MyTests() {
        int teacherId = Data.currentUser.id;
        System.out.println("\nТесты:");
        List<Test> tests = Data.tests;
        boolean found = false;

        for (Test test : tests) {
            if (test.authorId == teacherId) {
                System.out.println(test);
                found = true;
            }
        }
    }

    public static void MyResults() {
        int teacherId = Data.currentUser.id;
        System.out.println("\nРезультаты по тестам:");
        List<Test> tests = Data.tests;
        List<Result> results = Data.results;
        boolean found = false;

        for (Test test : tests) {
            if (test.authorId == teacherId) {
                System.out.println("\n> " + test.title + ":");
                boolean hasResults = false;

                for (Result res : results) {
                    if (res.testnumber== test.number) {
                        System.out.println("  " + res);
                        hasResults = true;
                        found = true;
                    }
                }

            }
        }
    }
}
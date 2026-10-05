package controller;

import model.*;
import util.Database;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Controller responsible for executing tests, generating detailed reports,
 * and saving results to the database.
 */
public class TestExecutionController {
    private final Database database;
    private final RatingController ratingController; // Нужен для пересчета рейтинга

    /**
     * Constructs a {@code TestExecutionController}.
     *
     * @param database        the data repository
     * @param ratingController the controller for calculating student ratings
     */
    public TestExecutionController(Database database, RatingController ratingController) {
        this.database = database;
        this.ratingController = ratingController;
    }

    /**
     * Runs a test for a specific student, generates a full report, saves the result,
     * and updates the student's main rating.
     *
     * @param studentId the ID of the student taking the test
     * @param testId    the ID of the test to be executed
     * @param scanner   the console scanner for user input
     * @return the resulting {@code TestResult} object, or {@code null} if the test was not found
     */
    public TestResult runTest(int studentId, int testId, Scanner scanner) {
        Test test = database.getTestById(testId);
        if (test == null) {
            System.out.println("Тест не найден.");
            return null;
        }

        System.out.println("   ТЕСТИРОВАНИЕ: " + test.getName());
        System.out.println("Вводите номера вариантов через пробел (например: 1 3).");
        System.out.println("Нажмите Enter без ввода, чтобы пропустить вопрос.\n");

        double totalScore = 0.0;
        double maxPossibleScore = test.getMaxScore();

        if (maxPossibleScore == 0) {
            maxPossibleScore = test.getQuestions().size() * 10.0;
        }

        List<QuestionReportItem> questionReports = new ArrayList<>();
        List<Question> questions = test.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);

            System.out.printf("--- Вопрос %d из %d ---%n", i + 1, questions.size());
            System.out.println(q.getText());

            for (int j = 0; j < q.getOptions().size(); j++) {
                System.out.printf("  [%d] %s%n", j + 1, q.getOptions().get(j));
            }

            System.out.print("Ваш ответ: ");
            String input = scanner.nextLine().trim();

            List<Integer> userIndices = parseUserInput(input);

            String status;
            double questionScore = 0.0;

            if (userIndices.isEmpty()) {
                status = "Skipped";
            } else {
                boolean isCorrect = q.isAnswerCorrect(userIndices);

                if (isCorrect) {
                    status = "Correct";
                    questionScore = 10.0;
                } else {
                    boolean hasAnyCorrect = false;
                    for (Integer idx : userIndices) {
                        if (q.getCorrectOptionIndices().contains(idx)) {
                            hasAnyCorrect = true;
                            break;
                        }
                    }

                    if (hasAnyCorrect) {
                        status = "Partly Correct";
                        questionScore = 0.0;
                    } else {
                        status = "Incorrect";
                        questionScore = 0.0;
                    }
                }
                totalScore += questionScore;
            }

            questionReports.add(new QuestionReportItem(i + 1, q.getText(), status, questionScore));

            if ("Skipped".equals(status)) System.out.println("Пропущено");
            else if ("Correct".equals(status)) System.out.println("Верно");
            else System.out.println("Неверно");

            System.out.println();
        }

        long correctCount = questionReports.stream()
                .filter(q -> "Correct".equals(q.getStatus()))
                .count();

        double percentage = (questions.size() > 0) ? ((double) correctCount / questions.size()) * 100.0 : 0.0;

        String testStatus = (percentage >= 50.0) ? "PASSED" : "FAILED";

        printFullReport(test.getName(), testStatus, percentage, totalScore, maxPossibleScore, questionReports);

        int newResultId = database.getTestResults().size() + 1;
        TestResult result = new TestResult(newResultId, studentId, testId, totalScore);

        database.addTestResult(result);
        System.out.println("\n Результат успешно сохранен в базу данных.");

        double newRatingValue = ratingController.calculateStudentRating(studentId);
        System.out.printf("Рейтинг студента обновлен: %.2f / 10.0%n", newRatingValue);

        return result;
    }

    /**
     * Prints a formatted full report to the console.
     *
     * @param testName   the name of the test being reported
     * @param status     the overall test status ({@code "PASSED"} or {@code "FAILED"})
     * @param percentage the percentage of correctly answered questions (0.0–100.0)
     * @param totalScore the total score achieved by the student in this test attempt
     * @param maxScore   the maximum possible score for this test
     * @param items      a list of {@code QuestionReportItem} objects containing per-question details
     */
    private void printFullReport(String testName, String status, double percentage,
                                 double totalScore, double maxScore, List<QuestionReportItem> items) {
        System.out.println("ПОЛНЫЙ ОТЧЕТ ПО ТЕСТУ:");
        System.out.printf("Тест: %s%n", testName);
        System.out.printf("Статус: %s%n", status);
        System.out.printf("Баллы: %.1f из %.1f%n", totalScore, maxScore);
        System.out.printf("Процент верных ответов: %.2f%%%n", percentage);
        System.out.println("Детализация по вопросам:");

        for (QuestionReportItem item : items) {
            String shortText = item.questionText.length() > 40
                    ? item.questionText.substring(0, 37) + "..."
                    : item.questionText;

            System.out.printf("  #%d: %-40s | %-15s | %.1f б.%n",
                    item.questionNumber, shortText, item.status, item.score);
        }
    }

    /**
     * Parses user input string into a list of 0-based indices.
     *
     * @param input the raw user input string containing selected option numbers
     * @return a list of 0-based indices corresponding to valid selected options; empty if no valid input provided
     */
    private List<Integer> parseUserInput(String input) {
        List<Integer> indices = new ArrayList<>();
        if (input == null || input.trim().isEmpty()) {
            return indices;
        }

        String[] parts = input.split("[\\s,]+");
        for (String part : parts) {
            try {
                int num = Integer.parseInt(part.trim());
                if (num > 0) {
                    indices.add(num - 1);
                }
            } catch (NumberFormatException e) {
            }
        }
        return indices;
    }

    /**
     * Inner class to hold details for the report.
     */
    private static class QuestionReportItem {
        int questionNumber;
        String questionText;
        String status;
        double score;

        public QuestionReportItem(int questionNumber, String questionText, String status, double score) {
            this.questionNumber = questionNumber;
            this.questionText = questionText;
            this.status = status;
            this.score = score;
        }

        public String getStatus() { return status; }
    }
}
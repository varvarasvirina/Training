package controller;

import model.StudentRating;
import model.Student;
import util.Database;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for calculating and managing academic ratings.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class RatingController {
    private final Database database;
    private final TestController testController;

    /**
     * Constructs a {@code RatingController} with dependencies.
     *
     * @param database       the data repository
     * @param testController the controller for accessing test metadata
     */
    public RatingController(Database database, TestController testController) {
        this.database = database;
        this.testController = testController;
    }

    /**
     * Calculates the normalized academic rating for a student on a scale of 0.0 to 10.0.
     *
     * @param studentId the identifier of the student
     * @return the calculated rating clamped between 0.0 and 10.0
     */
    public double calculateStudentRating(int studentId) {
        var results = database.getResultsByStudentId(studentId);
        if (results.isEmpty()) return 0.0;

        double totalScore = 0.0;
        double totalMax = 0.0;

        for (var res : results) {
            var test = testController.getTestById(res.getTestId());
            if (test != null) {
                totalScore += res.getScore();
                totalMax += test.getMaxScore();
            }
        }
        if (totalMax == 0.0) return 0.0;

        double raw = (totalScore / totalMax) * 10.0;
        return Math.max(0.0, Math.min(10.0, Math.round(raw * 100.0) / 100.0));
    }

    /**
     * Retrieves all students in a group with their ratings, sorted in descending order.
     *
     * @param groupId the identifier of the target group
     * @return a list of {@code StudentRating} objects sorted by rating (DESC)
     */
    public List<StudentRating> getGroupRatingsSortedDesc(int groupId) {
        List<Student> students = database.getStudentsByGroupId(groupId);
        List<StudentRating> ratings = new ArrayList<>();

        for (Student s : students) {
            ratings.add(new StudentRating(s, calculateStudentRating(s.getId())));
        }

        sortManuallyDescending(ratings);
        return ratings;
    }

    /**
     * Performs manual bubble sort on the list of ratings in descending order.
     *
     * @param list the list of {@code StudentRating} to sort
     */
    private void sortManuallyDescending(List<StudentRating> list) {
        int n = list.size();

        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getRating() < list.get(j + 1).getRating()) {
                    StudentRating temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);

                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
}
package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The controller for calculating academic ratings.
 *
 *  @version 1.10 27 March 2026
 *  @author Varvara Svirina
 */
public class Controller {
    private final Database database;

    /**
     * Constructor a {@code Controller} with the database.
     *
     * @param database экземпляр InMemoryDatabase
     */
    public Controller(Database database) {
        this.database = database;
    }

    /**
     * Calculates the normalized student rating in the range of 0.0..10.0.
     * Formula: (sum of points scored / sum of maximum points) * 10.
     *
     * @param studentId student ID
     * @return rating from 0.0 to 10.0, rounded to 2 digits
     */
    public double calculateStudentRating(int studentId) {
        List<TestResult> results = database.getTestResultsByStudentId(studentId);
        if (results.isEmpty()) return 0.0;

        double totalAchieved = 0.0;
        double totalMax = 0.0;

        for (TestResult result : results) {
            Test test = database.getTestById(result.getTestId());
            if (test != null) {
                totalAchieved += result.getScore();
                totalMax += test.getMaxScore();
            }
        }

        if (totalMax == 0.0) return 0.0;

        double rawRating = (totalAchieved / totalMax) * 10.0;

        return Math.max(0.0, Math.min(10.0, Math.round(rawRating * 100.0) / 100.0));
    }

    /**
     * Returns a descending list of student ratings for the specified group.
     *
     * @param groupId group ID.
     * @return StudentRating list, manually sorted by descending rating
     */
    public List<StudentRating> getGroupRatingsDescending(int groupId) {
        List<Student> groupStudents = database.getStudentsByGroupId(groupId);
        List<StudentRating> ratings = new ArrayList<>();

        for (Student student : groupStudents) {
            double rating = calculateStudentRating(student.getId());
            ratings.add(new StudentRating(student, rating));
        }

        sortRatingsManuallyDescending(ratings);
        return ratings;
    }

    /** Returns the available groups (the View is used to display the selection).
     *
     * @return available groups
     */
    public List<Group> getAvailableGroups() {
        return database.getGroups();
    }

    /**
     * Performs manual bubble sorting in descending order of rating.
     *
     * @param ratings the list to sort
     */
    private void sortRatingsManuallyDescending(List<StudentRating> ratings) {
        int size = ratings.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (ratings.get(j).getRating() < ratings.get(j + 1).getRating()) {
                    StudentRating temp = ratings.get(j);
                    ratings.set(j, ratings.get(j + 1));
                    ratings.set(j + 1, temp);
                }
            }
        }
    }
}
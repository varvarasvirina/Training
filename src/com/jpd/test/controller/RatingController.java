package com.jpd.test.controller;

import com.jpd.test.model.*;
import com.jpd.test.util.Database;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for calculating and managing academic ratings (0..10 scale)
 * and sorting students within a group manually.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class RatingController {

    /**
     * Calculates the normalized academic rating for a student on a scale of 0.0 to 10.0.
     *
     * @param studentId the identifier of the student
     * @return the calculated rating clamped between 0.0 and 10.0
     */
    public double calculateStudentRating(int studentId) {
        List<TestResult> results = Database.results.stream()
                .filter(r -> r.getStudentId() == studentId)
                .toList();

        if (results.isEmpty()) return 0.0;

        double totalScore = 0.0;
        double totalMax = 0.0;

        for (TestResult res : results) {
            Test test = Database.tests.stream()
                    .filter(t -> t.getId() == res.getTestId())
                    .findFirst()
                    .orElse(null);

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

        List<User> students = Database.users.stream()
                .filter(u -> u.getRole() == Role.STUDENT && u.getGroupId() == groupId)
                .toList();

        List<StudentRating> ratings = new ArrayList<>();

        for (User s : students) {
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
package com.jpd.test.controller;

import com.jpd.test.model.*;
import com.jpd.test.util.Database;
import com.jpd.test.view.ConsoleView;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles business logic and data retrieval for the Teacher role.
 * Includes filtering of personal tests and aggregation of student results.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class TeacherController {
    private final ConsoleView view;
    private final RatingController ratingController;
    private static final int CURRENT_TEACHER_ID = 2;

    /**
     * Constructs a {@code TeacherController} with the specified view dependency.
     *
     * @param view the {@code ConsoleView} instance for displaying teacher-specific data
     */
    public TeacherController(ConsoleView view) {

        this.view = view;
        this.ratingController = new RatingController();
    }

    /**
     * Executes the requested action for the teacher role.
     *
     * @param action the string identifier of the action to perform
     */
    public void execute(String action) {
        if ("2".equals(action)) {
            List<Test> myTests = Database.tests.stream()
                    .filter(t -> t.getTeacherId() == CURRENT_TEACHER_ID)
                    .collect(Collectors.toList());

            view.showTeacherDashboard(myTests, Database.results, Database.users);
        }
        else if ("3".equals(action)) {
            view.showGroupList(Database.groups);
            int groupId = view.promptGroupId();
            String groupName = Database.groups.stream()
                    .filter(g -> g.getId() == groupId)
                    .map(Group::getName)
                    .findFirst().orElse("Группа " + groupId);

            List<StudentRating> ratings = ratingController.getGroupRatingsSortedDesc(groupId);
            view.printGroupRating(groupName, ratings);
        }
        else {
            view.printError();
        }
    }
}
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Simulation of an in-memory database.
 * Encapsulates the storage and delivery of learning entities
 *
 *  @version 1.10 28 March 2026
 *  @author Varvara Svirina
 */
public class Database {
    private final List<Group> groups = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    private final List<Test> tests = new ArrayList<>();
    private final List<TestResult> testResults = new ArrayList<>();

    /**
     * Constructor a {@code Database}.
     */
    public Database() {
        populateInitialData();
    }

    /** Fills the database with initial data about groups, students, tests, and results. */
    private void populateInitialData() {
        groups.add(new Group(1, "6109-020302D"));
        groups.add(new Group(2, "7211-380203V"));

        students.add(new Student(1, "Наталья", 1));
        students.add(new Student(2, "Борис", 1));
        students.add(new Student(3, "Виктор", 2));
        students.add(new Student(4, "Анастасия", 2));

        tests.add(new Test(1, "Математический анализ", 100.0));
        tests.add(new Test(2, "Основы программирования", 100.0));

        testResults.add(new TestResult(1, 1, 1, 90.0));
        testResults.add(new TestResult(2, 1, 2, 80.0));
        testResults.add(new TestResult(3, 2, 1, 70.0));
        testResults.add(new TestResult(4, 2, 2, 60.0));
        testResults.add(new TestResult(5, 3, 1, 95.0));
        testResults.add(new TestResult(6, 3, 2, 90.0));
        testResults.add(new TestResult(7, 4, 1, 50.0));
        testResults.add(new TestResult(8, 4, 2, 40.0));
    }

    /** Returns a copy of the list of all groups.
     *
     * @return the list of all groups
     */
    public List<Group> getGroups() {
        return new ArrayList<>(groups);
    }

    /** Retrieves the list of students belonging to the specified group.
     *
     * @return the list of students
     */
    public List<Student> getStudentsByGroupId(int groupId) {
        List<Student> filtered = new ArrayList<>();
        for (Student s : students) {
            if (s.getGroupId() == groupId) filtered.add(s);
        }
        return filtered;
    }

    /** Returns the test by its ID, or null if not found.
     *
     * @return null
     */
    public Test getTestById(int testId) {
        for (Test t : tests) {
            if (t.getId() == testId) return t;
        }
        return null;
    }

    /** Returns all test results for the specified student.
     *
     * @return filtered test results
     */
    public List<TestResult> getTestResultsByStudentId(int studentId) {
        List<TestResult> filtered = new ArrayList<>();
        for (TestResult r : testResults) {
            if (r.getStudentId() == studentId) filtered.add(r);
        }
        return filtered;
    }
}
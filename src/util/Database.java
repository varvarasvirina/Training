package util;

import model.*;
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
    public Database() { seedData(); }

    /** Fills the database with initial data about groups, students, tests, and results. */
    private void seedData() {
        groups.add(new Group(1, "6109-020302D"));
        groups.add(new Group(2, "7211-380203V"));

        students.add(new Student(1, "Наталья", 1));
        students.add(new Student(2, "Борис", 1));
        students.add(new Student(3, "Виктор", 2));
        students.add(new Student(4, "Анастасия", 2));

        List<String> opts1 = List.of("Java", "Python", "C++", "Go");
        Question q1 = new Question(1, "Какие языки компилируются в байт-код JVM?", opts1, List.of(0, 3));

        List<String> opts2 = List.of("HTTP", "FTP", "SMTP", "SSH");
        Question q2 = new Question(2, "Основной протокол веба?", opts2, List.of(0));

        List<String> opts3 = List.of("ArrayList", "LinkedList", "HashMap", "int[]");
        Question q3 = new Question(3, "Классы коллекции Java?", opts3, List.of(0, 1, 2));

        List<Question> test1Questions = List.of(q1, q2, q3);
        tests.add(new Test(1, "Основы Java", test1Questions));

        testResults.add(new TestResult(1, 1, 1, 10.0));
        testResults.add(new TestResult(2, 1, 2, 30.0));
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
    public List<Group> getGroups() { return new ArrayList<>(groups); }

    /** Retrieves a specific [Group] by its unique identifier.
     *
     * @param id the unique identifier of the group to find
     * @return the [Group] object if found, or {@code null} if no group with the specified ID exists
     */
    public Group getGroupById(int id) {
        for (Group g : groups) if (g.getId() == id) return g;
        return null;
    }

    /** Retrieves all students from the database.
     *
     * @return a new [ArrayList] containing all [Student] objects
     */
    public List<Student> getStudents() { return new ArrayList<>(students); }

    /** Retrieves all students belonging to a specific [Group].
     *
     * @param groupId the identifier of the target group
     * @return a new [ArrayList] containing all [Student] objects associated with the specified group ID
     */
    public List<Student> getStudentsByGroupId(int groupId) {
        List<Student> filtered = new ArrayList<>();
        for (Student s : students) if (s.getGroupId() == groupId) filtered.add(s);
        return filtered;
    }

    /**
     * Retrieves a specific {@code Student} by their unique identifier.
     *
     * @param id the unique identifier of the student to find
     * @return the {@code Student} object if found, or {@code null} if no student with the specified ID exists
     */
    public Student getStudentById(int id) {
        for (Student s : students) if (s.getId() == id) return s;
        return null;
    }

    /**
     * Retrieves all tests from the database.
     *
     * @return a new {@code ArrayList} containing all {@code Test} objects
     */
    public List<Test> getTests() { return new ArrayList<>(tests); }

    /**
     * Retrieves a specific {@code Test} by its unique identifier.
     *
     * @param id the unique identifier of the test to find
     * @return the {@code Test} object if found, or {@code null} if no test with the specified ID exists
     */
    public Test getTestById(int id) {
        for (Test t : tests) if (t.getId() == id) return t;
        return null;
    }

    /**
     * Retrieves all test results from the database.
     *
     * @return a new {@code ArrayList} containing all {@code TestResult} objects
     */
    public List<TestResult> getTestResults() { return new ArrayList<>(testResults); }


    /** Returns all test results for the specified student.
     *
     * @param studentId the identifier of the student whose results are requested
     * @return filtered test results
     */
    public List<TestResult> getResultsByStudentId(int studentId) {
        List<TestResult> filtered = new ArrayList<>();
        for (TestResult r : testResults) if (r.getStudentId() == studentId) filtered.add(r);
        return filtered;
    }

    /**
     * Adds a new test result to the database.
     *
     * @param result the {@code TestResult} object to add
     */
    public void addTestResult(TestResult result) {
        testResults.add(result);
    }
}
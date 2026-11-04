package com.jpd.test.util;

import com.jpd.test.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulation of an in-memory database.
 * Encapsulates the storage and retrieval of learning entities.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class Database {

    public static final List<User> users = new ArrayList<>();
    public static final List<Test> tests = new ArrayList<>();
    public static final List<TestResult> results = new ArrayList<>();
    public static final List<Group> groups = new ArrayList<>();

    /**
     * Static initialization block.
     * Automatically populates the collections with sample data when the class is loaded.
     */
    static {
        seedData();
    }

    /**
     * Initializes the static collections with a predefined set of sample data.
     * This method is executed exactly once during application startup.
     */
    private static void seedData() {

        groups.add(new Group(1, "6401-020302"));
        groups.add(new Group(2, "6402-20302"));

        users.add(new User(1, "Алексей", 1, Role.STUDENT));
        users.add(new User(2, "Борис", 1, Role.STUDENT));
        users.add(new User(3, "Виктория", 2, Role.STUDENT));
        users.add(new User(4, "Галина", 2, Role.STUDENT));

        users.add(new User(5, "Иван Николаевич", 0, Role.TEACHER));
        users.add(new User(6, "Мария Ивановна", 0, Role.ADMIN));

        List<Question> questionsJava = List.of(
                new Question(1, "Что такое JVM?"),
                new Question(2, "Какой тип данных является примитивным?")
        );
        tests.add(new Test(1, "Основы Java", 2, questionsJava, 100.0));

        List<Question> questionsOop = List.of(
                new Question(3, "Что такое инкапсуляция?"),
                new Question(4, "Что такое полиморфизм?"),
                new Question(5, "Что такое наследование?")
        );
        tests.add(new Test(2, "ООП Принципы", 2, questionsOop, 100.0));

        results.add(new TestResult(1, 1, 1, 90));
        results.add(new TestResult(2, 2, 2, 85));
        results.add(new TestResult(3, 3, 1, 74));
        results.add(new TestResult(4, 4, 2, 95));
    }
}
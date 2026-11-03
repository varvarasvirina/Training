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
        users.add(new User(1, "Анна (Студент)", Role.STUDENT));
        users.add(new User(2, "Иван (Преподаватель)", Role.TEACHER));
        users.add(new User(3, "Мария (Администратор)", Role.ADMIN));

        List<Question> questionsJava = List.of(
                new Question(1, "Что такое JVM?"),
                new Question(2, "Какой тип данных является примитивным?")
        );
        tests.add(new Test(1, "Основы Java", 2, questionsJava));

        List<Question> questionsOop = List.of(
                new Question(3, "Что такое инкапсуляция?"),
                new Question(4, "Что такое полиморфизм?"),
                new Question(5, "Что такое наследование?")
        );
        tests.add(new Test(2, "ООП Принципы", 2, questionsOop));

        results.add(new TestResult(1, 1, 1, 9.0));
        results.add(new TestResult(1, 2, 2, 8.5));
    }
}
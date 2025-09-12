public class StsrtData {
    public static void init() {
        // Пользователи
        Data.users.add(new User("Александрова Мария ", Users.STUDENT, 1  ));
        Data.users.add(new User("Иванов Михаил", Users.STUDENT, 2));
        Data.users.add(new User("Смирнов Валерий", Users.TEACHER, 3));
        Data.users.add(new User("Морозова Елена", Users.TEACHER, 4));
        Data.users.add(new User("Администратор", Users.ADMIN, 5));

        // Тесты
        Data.tests.add(new Test(1, "ООП", 3, 10));
        Data.tests.add(new Test(2, "База данных", 4, 8));
        Data.tests.add(new Test(3, "Алгоритмы", 3, 12));

        // Результаты
        Data.results.add(new Result(1, 1, 73));
        Data.results.add(new Result(1, 2, 90));
        Data.results.add(new Result(1, 3, 54));
        Data.results.add(new Result(2, 1, 89));
        Data.results.add(new Result(2, 2, 68));
        Data.results.add(new Result(2, 3, 93));
    }
}
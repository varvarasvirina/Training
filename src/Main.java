import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        StsrtData.init(); // Инициализируем данные
        System.out.println("Система дистанционного тестирования");

        while (true) {
            chooseUser(); // Пользователь выбирает себя по ID
            if (Data.currentUser == null) {
                break; // Выход из программы
            }

            showRoleMenu(); // Показываем меню по роли
        }

        System.out.println("До свидания!");
        scanner.close();
    }

    // Выбор пользователя из списка
    private static void chooseUser() {
        System.out.println("Выберете пользователя:");
        for (User user : Data.users) {
            System.out.println(user);
        }
        System.out.println("0 Выйти из программы");
        System.out.print("Введите ID пользователя: ");

        int id = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        if (id == 0) {
            Data.currentUser = null;
            return;
        }

        for (User user : Data.users) {
            if (user.id == id) {
                Data.currentUser = user;
                System.out.println("Вы вошли как: " + user.name);
                return;
            }
        }

        System.out.println("Пользователь с ID " + id + " не найден.");
        Data.currentUser = null;
    }

    // Меню в зависимости от роли
    private static void showRoleMenu() {
        Users role = Data.currentUser.person;

        switch (role) {
            case STUDENT:
                studentMenu();
                break;
            case TEACHER:
                teacherMenu();
                break;
            case ADMIN:
                adminMenu();
                break;
        }
    }

    // Меню студента
    private static void studentMenu() {
        while (true) {
            System.out.println("\n    МЕНЮ СТУДЕНТА [" + Data.currentUser.name +"]    ");
            System.out.println("1. Просмотреть список тестов");
            System.out.println("0. Вернуться к выбору пользователя");
            System.out.print("Выберите: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Student.showTests();
                    break;
                case 0:
                    Data.currentUser = null;
                    return;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    // Меню преподавателя
    private static void teacherMenu() {
        while (true) {
            System.out.println("\n    МЕНЮ ПРЕПОДАВАТЕЛЯ [" + Data.currentUser.name +"]    ");
            System.out.println("1. Мои тесты");
            System.out.println("2. Результаты по моим тестам");
            System.out.println("0. Вернуться к выбору пользователя");
            System.out.print("Выберите: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Teacher.MyTests();
                    break;
                case 2:
                    Teacher.MyResults();
                    break;
                case 0:
                    Data.currentUser = null;
                    return;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    // Меню администратора
    private static void adminMenu() {
        while (true) {
            System.out.println("\n    МЕНЮ АДМИНИСТРАТОРА [" + Data.currentUser.name + "]    ");
            System.out.println("1. Список всех пользователей");
            System.out.println("0. Вернуться к выбору пользователя");
            System.out.print("Выберите: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Admin.showAllUsers();
                    break;
                case 0:
                    Data.currentUser = null;
                    return;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }
}
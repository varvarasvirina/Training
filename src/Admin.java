import java.util.List;

public class Admin{
    public static void showAllUsers() {
        System.out.println("\nВсе пользователи:");
        List<User> users = Data.users;
        for (User user : users) {
            System.out.println(user);
        }
    }
}
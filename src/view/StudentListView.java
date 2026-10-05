package view;

import model.Student;
import model.Group;
import java.util.List;

/**
 * View component for displaying a list of students with their associated groups.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class StudentListView {

    /**
     * Renders a formatted table of students with their group names.
     *
     * @param students the list of {@code Student} objects to display
     * @param groups   the list of {@code Group} objects used for name resolution
     */
    public void renderList(List<Student> students, List<Group> groups) {
        System.out.println("\n Все студенты:");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-5s | %-15s | %-5s | %s%n", "ID", "Имя", "Группа ID", "Группа");
        System.out.println("------------------------------------------------------------");

        for (Student s : students) {
            String groupName = "Неизвестный";
            for (Group g : groups) {
                if (g.getId() == s.getGroupId()) {
                    groupName = g.getName();
                    break;
                }
            }
            System.out.printf("%-5d | %-15s | %-5d | %s%n",
                    s.getId(), s.getName(), s.getGroupId(), groupName);
        }
    }
}
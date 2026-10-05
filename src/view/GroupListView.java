package view;

import model.Group;
import java.util.List;

/**
 * View component for displaying a list of academic groups.
 *
 * @version 1.10 23 June 2026
 * @author Varvara Svirina
 */
public class GroupListView {

    /**
     * Renders a formatted table of groups.
     *
     * @param groups the list of {@code Group} objects to display
     */
    public void renderList(List<Group> groups) {
        System.out.println("\nСписок всех групп:");
        System.out.println("----------------------------------------------");
        System.out.printf("%-5s | %s%n", "ID", "Название");
        System.out.println("----------------------------------------------");
        for (Group g : groups) {
            System.out.printf("%-5d | %s%n", g.getId(), g.getName());
        }
    }
}
package view;

import model.StudentRating;
import java.util.List;

/**
 * View component for rendering academic rating tables.
 *
 * @version 1.10 28 March 2026
 * @author Varvara Svirina
 */
public class RatingTableView {

    /**
     * Renders a formatted table of student ratings.
     *
     * @param ratings the list of {@code StudentRating} objects to display
     */
    public void renderTable(List<StudentRating> ratings) {
        if (ratings.isEmpty()) {
            System.out.println("В этой группе нет студентов.");
            return;
        }

        System.out.println("\nРейтинги группы ID: " + ratings.get(0).getStudent().getGroupId());
        System.out.println("----------------------------------------------");
        System.out.printf("%-15s | %-12s%n", "Студент", "Рейтинг (0-10)");
        System.out.println("----------------------------------------------");

        for (StudentRating sr : ratings) {
            System.out.printf("%-15s | %-12.2f%n",
                    sr.getStudent().getName(),
                    sr.getRating());
        }
    }
}
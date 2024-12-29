import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DaysPassed {
    public static void main(String[] args) {
        // Specify the given date
        LocalDate givenDate = LocalDate.of(2023, 12, 1); // Example: Dec 1, 2023

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate days between the dates
        int daysPassed = (int) ChronoUnit.DAYS.between(givenDate, currentDate);

        System.out.println("Days passed since " + givenDate + ": " + daysPassed);
    }
}

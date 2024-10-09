package exceptions;
import java.time.LocalDate;

class ApplicationDeadlineException extends Exception {
    public ApplicationDeadlineException(String message) {
        super(message);
    }
}

public class JobApplication {
    public static void main(String[] args) {
        LocalDate deadline = LocalDate.of(2024, 10, 15); // Example deadline
        LocalDate applicationDate = LocalDate.of(2024, 10, 20); // Example application date
        
        try {
            submitApplication(applicationDate, deadline);
            System.out.println("Application submitted successfully.");
        } catch (ApplicationDeadlineException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void submitApplication(LocalDate applicationDate, LocalDate deadline) throws ApplicationDeadlineException {
        if (applicationDate.isAfter(deadline)) {
            throw new ApplicationDeadlineException("Application cannot be submitted after the deadline: " + deadline);
        }
        // Logic to submit the application
    }
}

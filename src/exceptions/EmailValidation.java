package exceptions;
import java.util.Scanner;
import java.util.regex.Pattern;

class InvalidEmailFormatException extends Exception {
    public InvalidEmailFormatException(String message) {
        super(message);
    }
}

public class EmailValidation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        
        try {
            validateEmail(email);
            System.out.println("Email is valid. Proceeding with registration...");
            // Registration logic here
        } catch (InvalidEmailFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        scanner.close();
    }

    public static void validateEmail(String email) throws InvalidEmailFormatException {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(emailRegex, email)) {
            throw new InvalidEmailFormatException("Invalid email format. Please enter a valid email address.");
        }
    }
}

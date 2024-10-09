package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ApplicantProfileCreation {
    private static final String URL = "jdbc:mysql://localhost:3306/JobBoard"; // Your database URL
    private static final String USER = "Harika"; // Your username
    private static final String PASSWORD = "harika@198189"; // Your password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Collect applicant information
        System.out.print("Enter applicant name: ");
        String name = scanner.nextLine();

        System.out.print("Enter applicant email: ");
        String email = scanner.nextLine();

        System.out.print("Enter applicant phone number: ");
        String phone = scanner.nextLine();

        // SQL query for inserting applicant
        String query = "INSERT INTO Applicants (name, email, phone) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set parameters
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);

            // Execute the query
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Applicant profile created successfully!");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

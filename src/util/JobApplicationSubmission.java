package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JobApplicationSubmission {
    private static final String URL = "jdbc:mysql://localhost:3306/JobBoard"; // Your database URL
    private static final String USER = "Harika"; // Your username
    private static final String PASSWORD = "harika@198189"; // Your password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get applicant ID
        System.out.print("Enter applicant ID: ");
        int applicantId = scanner.nextInt();
        
        // Display available jobs
        displayJobListings();

        // Get job ID to apply for
        System.out.print("Enter job ID to apply for: ");
        int jobId = scanner.nextInt();

        // SQL query for inserting application
        String query = "INSERT INTO Applications (applicant_id, job_id) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set parameters
            statement.setInt(1, applicantId);
            statement.setInt(2, jobId);

            // Execute the query
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Job application submitted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void displayJobListings() {
        String query = "SELECT id, job_title FROM Jobs";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Available Job Listings:");
            while (resultSet.next()) {
                int jobId = resultSet.getInt("id");
                String jobTitle = resultSet.getString("job_title");
                System.out.printf("Job ID: %d, Title: %s%n", jobId, jobTitle);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while retrieving job listings: " + e.getMessage());
        }
    }
}

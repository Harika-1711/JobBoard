package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CompanyJobPosting {
    private static final String URL = "jdbc:mysql://localhost:3306/JobBoard"; // Your database URL
    private static final String USER = "Harika"; // Your username
    private static final String PASSWORD = "harika@198189"; // Your password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Collect job listing details
        System.out.print("Enter job title: ");
        String jobTitle = scanner.nextLine();

        System.out.print("Enter company ID: ");
        int companyId = scanner.nextInt();

        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();

        // SQL query for inserting job listing
        String query = "INSERT INTO Jobs (job_title, company_id, salary) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set parameters
            statement.setString(1, jobTitle);
            statement.setInt(2, companyId);
            statement.setDouble(3, salary);

            // Execute the query
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Job posting created successfully!");
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

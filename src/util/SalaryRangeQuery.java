package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SalaryRangeQuery {
    private static final String URL = "jdbc:mysql://localhost:3306/JobBoard"; // Your database URL
    private static final String USER = "Harika"; // Your username
    private static final String PASSWORD = "harika@198189"; // Your password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Collect salary range
        System.out.print("Enter minimum salary: ");
        double minSalary = scanner.nextDouble();

        System.out.print("Enter maximum salary: ");
        double maxSalary = scanner.nextDouble();

        // SQL query for retrieving job listings within salary range
        String query = "SELECT j.job_title, c.company_name, j.salary " +
                       "FROM Jobs j " +
                       "JOIN Companies c ON j.company_id = c.id " +
                       "WHERE j.salary BETWEEN ? AND ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set parameters
            statement.setDouble(1, minSalary);
            statement.setDouble(2, maxSalary);

            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Job Listings within Salary Range:");
                boolean hasResults = false;
                while (resultSet.next()) {
                    String jobTitle = resultSet.getString("job_title");
                    String companyName = resultSet.getString("company_name");
                    double salary = resultSet.getDouble("salary");
                    System.out.printf("Job Title: %s, Company: %s, Salary: %.2f%n", jobTitle, companyName, salary);
                    hasResults = true;
                }
                if (!hasResults) {
                    System.out.println("No job listings found in this salary range.");
                }
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

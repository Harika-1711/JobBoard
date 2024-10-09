package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JobListingRetrieval {
    private static final String URL = "jdbc:mysql://localhost:3306/JobBoard"; // Use your database name
    private static final String USER = "Harika"; // Replace with your username
    private static final String PASSWORD = "harika@198189"; // Replace with your password

    public static void main(String[] args) {
        String query = "SELECT j.job_title, c.company_name, j.salary " +
                       "FROM Jobs j " +
                       "JOIN Companies c ON j.company_id = c.id";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String jobTitle = resultSet.getString("job_title");
                String companyName = resultSet.getString("company_name");
                double salary = resultSet.getDouble("salary");
                System.out.printf("Job Title: %s, Company: %s, Salary: %.2f%n", jobTitle, companyName, salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

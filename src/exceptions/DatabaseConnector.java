package exceptions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException(String message) {
        super(message);
    }
}

public class DatabaseConnector {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jobboard"; // Example DB URL
        String username = "Harika";
        String password = "harika@198189";
        
        try {
            connectToDatabase(url, username, password);
        //    System.out.println("Connected to the database successfully.");
        } catch (DatabaseConnectionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void connectToDatabase(String url, String username, String password) throws DatabaseConnectionException {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            // Logic to retrieve job listings
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Database connection error: " + e.getMessage());
        }
    }
}

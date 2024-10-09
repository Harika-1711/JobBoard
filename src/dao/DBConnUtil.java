package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnUtil {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jobboard";
        String username = "Harika";
        String password = "harika@198189";   
        return DriverManager.getConnection(url, username, password);
    }
}

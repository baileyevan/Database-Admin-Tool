package src;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = DatabaseInformation.getDBURL();
    private static final String USER = DatabaseInformation.getUsername();
    private static final String PASSWORD = DatabaseInformation.getPassword();

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

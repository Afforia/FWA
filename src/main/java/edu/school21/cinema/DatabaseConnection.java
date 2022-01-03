package edu.school21.cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection initializeDatabase()
        throws SQLException, ClassNotFoundException {
        // Initialize all the information regarding
        // Database Connection
        String dbDriver = "org.postgresql.Driver";
        String dbURL = "jdbc:postgresql://127.0.0.1:5432/postgres";
        String dbUsername = "postgres";
        String dbPassword = "123";

        Class.forName(dbDriver);
        return DriverManager.getConnection(dbURL,
            dbUsername,
            dbPassword);
    }
}

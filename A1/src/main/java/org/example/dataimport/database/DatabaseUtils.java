package org.example.dataimport.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections.
 */
public class DatabaseUtils {

    /**
     * Establishes a connection to the database using the provided URL, username, and password.
     *
     * @param dbUrl The URL of the database to connect to.
     * @param user The username for the database connection.
     * @param password The password for the database connection.
     * @return A Connection object representing the database connection.
     * @throws SQLException If a database access error occurs.
     */
    public Connection getConnection(String dbUrl, String user, String password) throws SQLException {
        return DriverManager.getConnection(dbUrl, user, password);
    }
}

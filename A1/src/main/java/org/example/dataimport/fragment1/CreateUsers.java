package org.example.dataimport.fragment1;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

/**
 * This class provides a method to create the "users" table in the database.
 */
public class CreateUsers {

    /**
     * Creates the "users" table in the specified database if it does not already exist.
     *
     * @param dbUrl The URL of the database to connect to.
     * @param user The username for the database connection.
     * @param password The password for the database connection.
     */
    public static void createUsersTable(String dbUrl, String user, String password) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "user_id BIGINT PRIMARY KEY,"
                + "name VARCHAR(100),"
                + "username VARCHAR(100),"
                + "user_bio TEXT,"
                + "verified_or_non_verified VARCHAR(50),"
                + "profile_url VARCHAR(255),"
                + "protected_or_non_protected VARCHAR(50),"
                + "user_followers INT,"
                + "user_following INT,"
                + "user_account_creation_date TIMESTAMP,"
                + "impressions INT"
                + ")";

        try (Connection connection = getConnection(dbUrl, user, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
            System.out.println("Users table created successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to create users table: " + e.getMessage());
        }
    }
}
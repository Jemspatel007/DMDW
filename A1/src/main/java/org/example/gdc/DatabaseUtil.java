package org.example.gdc;

import java.sql.*;

public class DatabaseUtil {
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "Jems@1205";

    /**
     * Executes the provided SQL query on the specified database URL.
     *
     * @param dbUrl The database URL where the query will be executed.
     * @param query The SQL query to execute.
     */
    public static void executeQuery(String dbUrl, String query) {
        if (dbUrl == null) {
            System.out.println("No database URL found for the given key.");
            return;
        }
        try (Connection connection = DriverManager.getConnection(dbUrl, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement startTransactionStatement = connection.prepareStatement("START TRANSACTION");
             PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatement commitTransactionStatement = connection.prepareStatement("COMMIT")) {

            startTransactionStatement.execute();
            System.out.println("Transaction started");

            ResultSet result = statement.executeQuery();
            printResultSet(query, result);

            commitTransactionStatement.execute();
            System.out.println("Transaction committed\n");
        } catch (Exception e) {
            System.out.println("Failed to fetch data from the database: " + e.getMessage());
        }
    }

    /**
     * Prints the retrieved data from the ResultSet based on the query type (tweets or users).
     *
     * @param query  The SQL query that was executed.
     * @param result The ResultSet containing the retrieved data.
     * @throws SQLException If there is an error accessing or processing the ResultSet.
     */
    private static void printResultSet(String query, ResultSet result) throws SQLException {
        System.out.println("Retrieved data:");
        while (result.next()) {
            if (query.toLowerCase().contains("tweets")) {
                System.out.println("tweet_id: " + result.getLong("tweet_id"));
                System.out.println("tweet_url: " + result.getString("tweet_url"));
                System.out.println("user_id: " + result.getLong("user_id"));
                System.out.println("tweet_posted_time_utc: " + result.getString("tweet_posted_time_utc"));
                System.out.println("tweet_location: " + result.getString("tweet_location"));
            } else if (query.toLowerCase().contains("users")) {
                System.out.println("user_id: " + result.getLong("user_id"));
                System.out.println("username: " + result.getString("username"));
                System.out.println("user_followers: " + result.getInt("user_followers"));
                System.out.println("user_following: " + result.getInt("user_following"));
                System.out.println("user_account_creation_date: " + result.getTimestamp("user_account_creation_date"));
            }
            System.out.println();
        }
    }
}
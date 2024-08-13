package org.example.dataimport.fragment2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

/**
 * This class is responsible for creating the "tweets" table in the database.
 */
public class CreateTweets {

    /**
     * Creates the "tweets" table in the database if it does not already exist.
     *
     * @param dbUrl    The URL of the database to connect to.
     * @param user     The username for the database connection.
     * @param password The password for the database connection.
     */
    public static void createTweetsTable(String dbUrl, String user, String password) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS tweets ("
                + "tweet_id BIGINT,"
                + "tweet_url VARCHAR(255),"
                + "tweet_posted_time_utc TIMESTAMP,"
                + "tweet_content TEXT,"
                + "tweet_type VARCHAR(50),"
                + "client VARCHAR(100),"
                + "retweets_received INT,"
                + "likes_received INT,"
                + "tweet_location TEXT,"
                + "tweet_language VARCHAR(50),"
                + "user_id BIGINT,"
                + "PRIMARY KEY (tweet_id, user_id)"
                + ")";

        try (Connection connection = getConnection(dbUrl, user, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
            System.out.println("Tweets table created successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to create tweets table: " + e.getMessage());
        }
    }
}
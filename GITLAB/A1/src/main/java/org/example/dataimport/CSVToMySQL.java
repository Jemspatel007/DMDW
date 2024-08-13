package org.example.dataimport;

import org.example.dataimport.database.DatabaseUtils;
import org.example.dataimport.fragment1.CreateUsers;
import org.example.dataimport.fragment1.UserInsertion;
import org.example.dataimport.fragment2.CreateTweets;
import org.example.dataimport.fragment2.TweetInsertion;
import org.example.dataimport.util.CSVUtils;

/**
 * This class serves as the main entry point for importing data from CSV files into the MySQL database.
 * It handles the creation of tables and the insertion of user and tweet data.
 */
public class CSVToMySQL {

    private static final String VM1_DB_URL = "jdbc:mysql://34.69.85.85:3306/socialmediajems";
    private static final String VM2_DB_URL = "jdbc:mysql://35.225.23.200:3306/socialmediajems";
    private static final String USER = "root";
    private static final String PASSWORD = "Jems@1205";
    private static final String VM1_USERS = "src/main/java/org/example/CSVFiles/fragment1_users.csv";
    private static final String VM2_TWEETS = "src/main/java/org/example/CSVFiles/fragment2_tweets.csv";

    /**
     * The main method that serves as the entry point for the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        addDataToDatabase();
    }

    /**
     * Creates the necessary tables in the database and inserts data from the specified CSV files.
     */
    public static void addDataToDatabase() {
        DatabaseUtils dbHandler = new DatabaseUtils();
        CSVUtils csvHandler = new CSVUtils();

        // Create tables
        CreateUsers.createUsersTable(VM1_DB_URL, USER, PASSWORD);
        CreateTweets.createTweetsTable(VM2_DB_URL, USER, PASSWORD);

        // Insert data
        UserInsertion userTask = new UserInsertion(VM1_DB_URL, USER, PASSWORD, VM1_USERS, dbHandler, csvHandler);
        userTask.insertUsers();

        TweetInsertion tweetTask = new TweetInsertion(VM2_DB_URL, USER, PASSWORD, VM2_TWEETS, dbHandler, csvHandler);
        tweetTask.insertTweets();
    }
}
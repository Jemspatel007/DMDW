package org.example.dataimport.fragment2;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.dataimport.database.DatabaseUtils;
import org.example.dataimport.util.CSVUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.example.dataimport.util.DataConversionUtil.convertToDateTime;

/**
 * This class is responsible for inserting tweet data from a CSV file into the "tweets" table in the database.
 */
public class TweetInsertion {

    private String dbUrl;
    private String user;
    private String password;
    private String filePath;
    private DatabaseUtils dbHandler;
    private CSVUtils csvHandler;

    /**
     * Constructs a new TweetInsertion object with the specified database connection details and file path.
     *
     * @param dbUrl     The URL of the database to connect to.
     * @param user      The username for the database connection.
     * @param password  The password for the database connection.
     * @param filePath  The path to the CSV file containing the tweet data.
     * @param dbHandler The database utility handler for managing connections.
     * @param csvHandler The CSV utility handler for reading the CSV file.
     */
    public TweetInsertion(String dbUrl, String user, String password, String filePath,
                          DatabaseUtils dbHandler, CSVUtils csvHandler) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
        this.filePath = filePath;
        this.dbHandler = dbHandler;
        this.csvHandler = csvHandler;
    }

    /**
     * Inserts tweets from the CSV file into the "tweets" table in the database.
     */
    public void insertTweets() {
        String insertQuery = "INSERT INTO tweets (tweet_id, tweet_url, tweet_posted_time_utc, tweet_content, tweet_type, client, retweets_received, likes_received, tweet_location, tweet_language, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbHandler.getConnection(dbUrl, user, password);
             CSVReader reader = csvHandler.createCSVReader(filePath);
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            String[] line;
            while ((line = reader.readNext()) != null) {
                statement.setLong(1, Long.parseLong(line[0].replace("\"", "")));
                statement.setString(2, line[1]);
                statement.setTimestamp(3, java.sql.Timestamp.valueOf(convertToDateTime(line[2].replace("\"", ""))));
                statement.setString(4, line[3]);
                statement.setString(5, line[4]);
                statement.setString(6, line[5]);
                statement.setInt(7, Integer.parseInt(line[6]));
                statement.setInt(8, Integer.parseInt(line[7]));
                statement.setString(9, line[8]);
                statement.setString(10, line[9]);
                statement.setLong(11, Long.parseLong(line[10].replace("\"", "")));
                statement.addBatch();
            }
            statement.executeBatch();
            System.out.println("Inserted all rows into the database (Tweets)");
        } catch (SQLException | IOException | CsvValidationException e) {
            System.out.println("Failed to insert data to the database: " + e.getMessage());
        }
    }
}

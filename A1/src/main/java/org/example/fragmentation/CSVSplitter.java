package org.example.fragmentation;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Splits an input CSV file into two separate CSV files for users and tweets.
 */
public class CSVSplitter {

    private static final String INPUT_CSV = "src/main/java/org/example/CSVFiles/sample.csv";
    private static final String USERS_CSV = "src/main/java/org/example/CSVFiles/fragment1_users.csv";
    private static final String TWEETS_CSV = "src/main/java/org/example/CSVFiles/fragment2_tweets.csv";

    /**
     * Main method to initiate the splitting of the CSV file.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        splitCSV(INPUT_CSV, USERS_CSV, TWEETS_CSV);
    }

    /**
     * Splits the input CSV file into two separate CSV files: one for users and one for tweets.
     *
     * @param inputCsv Path to the input CSV file.
     * @param usersCsv Path to the output CSV file for users.
     * @param tweetsCsv Path to the output CSV file for tweets.
     */
    public static void splitCSV(String inputCsv, String usersCsv, String tweetsCsv) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(inputCsv)).withSkipLines(1).build();
             CSVWriter usersWriter = new CSVWriter(new FileWriter(usersCsv));
             CSVWriter tweetsWriter = new CSVWriter(new FileWriter(tweetsCsv))) {

            String[] userColumns = {"User Id", "Name", "Username", "User Bio", "Verified or Non-Verified",
                    "Profile URL", "Protected or Non-protected", "User Followers",
                    "User Following", "User Account Creation Date"};
            usersWriter.writeNext(userColumns);

            String[] tweetColumns = {"Tweet Id", "Tweet URL", "Tweet Posted Time (UTC)", "Tweet Content",
                    "Tweet Type", "Client", "Retweets Received", "Likes Received",
                    "Tweet Location", "Tweet Language", "User Id"};
            tweetsWriter.writeNext(tweetColumns);

            String[] line;
            while ((line = reader.readNext()) != null) {

                String[] user = {line[10], line[11], line[12], line[13], line[14], line[15], line[16], line[17], line[18], line[19]};
                usersWriter.writeNext(user);

                String[] tweet = {line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8], line[9], line[10]};
                tweetsWriter.writeNext(tweet);
            }

            System.out.println("CSV files split successfully!");

        } catch (IOException e) {
            System.out.println("An error occurred while splitting the CSV file: " + e.getMessage());
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}

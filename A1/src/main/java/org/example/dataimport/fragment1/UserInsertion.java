package org.example.dataimport.fragment1;

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
 * This class is responsible for inserting user data from a CSV file into the "users" table in the database.
 */
public class UserInsertion {

    private String dbUrl;
    private String user;
    private String password;
    private String filePath;
    private DatabaseUtils dbHandler;
    private CSVUtils csvHandler;

    /**
     * Constructs an instance of UserInsertion with the specified database credentials, file path, and utility handlers.
     *
     * @param dbUrl      The URL of the database to connect to.
     * @param user       The username for the database connection.
     * @param password   The password for the database connection.
     * @param filePath   The file path of the CSV file containing user data.
     * @param dbHandler  The utility handler for database operations.
     * @param csvHandler The utility handler for CSV operations.
     */
    public UserInsertion(String dbUrl, String user, String password, String filePath,
                         DatabaseUtils dbHandler, CSVUtils csvHandler) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
        this.filePath = filePath;
        this.dbHandler = dbHandler;
        this.csvHandler = csvHandler;
    }

    /**
     * Inserts user data from the CSV file into the "users" table in the database.
     */
    public void insertUsers() {
        String insertQuery = "INSERT INTO users (user_id, name, username, user_bio, verified_or_non_verified, profile_url, protected_or_non_protected, user_followers, user_following, user_account_creation_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbHandler.getConnection(dbUrl, user, password);
             CSVReader reader = csvHandler.createCSVReader(filePath);
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            String[] line;
            while ((line = reader.readNext()) != null) {
                statement.setLong(1, Long.parseLong(line[0].replace("\"", "")));
                statement.setString(2, line[1]);
                statement.setString(3, line[2]);
                statement.setString(4, line[3]);
                statement.setString(5, line[4]);
                statement.setString(6, line[5]);
                statement.setString(7, line[6]);
                statement.setInt(8, Integer.parseInt(line[7]));
                statement.setInt(9, Integer.parseInt(line[8]));
                statement.setTimestamp(10, java.sql.Timestamp.valueOf(convertToDateTime(line[9].replace("\"", ""))));
                statement.addBatch();
            }
            statement.executeBatch();
            System.out.println("Inserted all rows into the database (Users)");
        } catch (SQLException | IOException e) {
            System.out.println("Failed to insert data into the database: " + e.getMessage());
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.A2.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Class to manage MongoDB connection.
 */
public class DatabaseConnection {
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    /**
     * Constructor to create a database connection.
     *
     * @param uri the MongoDB URI connection string.
     * @param dbName the name of the database to connect to.
     */
    public DatabaseConnection(String uri, String dbName) {
        mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase(dbName);
        System.out.println("Database Connection Created successfully");
    }

    /**
     * Gets the connected database instance.
     *
     * @return the connected MongoDatabase instance.
     */
    public MongoDatabase getDatabase() {
        return database;
    }

    /**
     * Closes the database connection.
     */
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
        System.out.println("Database Connection closed successfully");
    }
}
package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static void connectToMongoDB(String connectionString, String dbName) {
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(dbName);
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static void closeMongoDBConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}

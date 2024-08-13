package org.A2.database;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.List;

/**
 * Class to handle insertion of documents into MongoDB.
 */
public class MongoInsert {
    private final DatabaseConnection database;

    /**
     * Constructor to initialize MongoInsert with a database connection.
     *
     * @param database the DatabaseConnection instance to use for database operations.
     */
    public MongoInsert(DatabaseConnection database) {
        this.database = database;
    }

    /**
     * Inserts a list of articles into a specified collection.
     *
     * @param collectionName the name of the collection to insert documents into.
     * @param articles the list of Document objects to be inserted.
     */
    public void insertArticles(String collectionName, List<Document> articles) {
        MongoCollection<Document> collection = database.getDatabase().getCollection(collectionName);
        collection.insertMany(articles);
    }
}
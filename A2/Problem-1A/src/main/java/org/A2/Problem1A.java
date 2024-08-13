package org.A2;
import java.io.IOException;
import java.util.List;

import org.A2.database.DatabaseConnection;
import org.A2.database.MongoInsert;
import org.bson.Document;

import static org.A2.news.NewsReader.readArticles;
import static org.A2.utils.Constants.*;


public class Problem1A {
    public static void main(String[] args) throws IOException {

        DatabaseConnection databaseConnection = new DatabaseConnection(MONGO_URI, DATABASE_NAME);

        MongoInsert mongoInserter = new MongoInsert(databaseConnection);

        List<Document> readArticles = readArticles();

        if (readArticles.isEmpty()) {
            System.out.println("No articles found to insert.");
        } else {
            mongoInserter.insertArticles(COLLECTION_NAME, readArticles);
            System.out.println("Articles are inserted successfully");
        }
        databaseConnection.close();
    }
}
package org.example;

import com.mongodb.client.MongoDatabase;

public class Main {

    public static void main(String[] args) {
        // Initialize MongoDB connection
        MongoDBConnection.connectToMongoDB("mongodb://localhost:27017", "G-Store");

        // Get database instance
        MongoDatabase database = MongoDBConnection.getDatabase();

        // Initialize CRUD operations
        InvoiceCRUD invoiceCRUD = new InvoiceCRUD(database);

        // Perform CRUD operations
        invoiceCRUD.insert("Laptop", 2, 1200);
        invoiceCRUD.read("Laptop");
        invoiceCRUD.update("Laptop", "quantity", 100);
        invoiceCRUD.delete("Laptop");

        // Close the MongoDB connection
        MongoDBConnection.closeMongoDBConnection();
    }

}
package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class InvoiceCRUD {
    private MongoCollection<Document> collection;

    public InvoiceCRUD(MongoDatabase database) {
        this.collection = database.getCollection("Invoice");
    }

    public void insert(String item, int quantity, double price) {
        Document Invoice = new Document("item", item)
                .append("quantity", quantity)
                .append("price", price);
        collection.insertOne(Invoice);
        System.out.println("Document inserted successfully");
    }

    public void read(String item) {
        Document foundInvoice = collection.find(eq("item", item)).first();
        if (foundInvoice != null) {
            System.out.println("Found document: " + foundInvoice.toJson());
        } else {
            System.out.println("Document not found");
        }
    }

    public void update(String item, String field, Object newValue) {
        collection.updateOne(eq("item", item), set(field, newValue));
        System.out.println("Document updated successfully");
        Document updatedInvoice = collection.find(eq("item", item)).first();
        if (updatedInvoice != null) {
            System.out.println("Updated document: " + updatedInvoice.toJson());
        } else {
            System.out.println("Document not found");
        }
    }

    public void delete(String item) {
        collection.deleteOne(eq("item", item));
        System.out.println("Document deleted successfully");
    }
}

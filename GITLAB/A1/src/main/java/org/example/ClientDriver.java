package org.example;

import org.example.gdc.GDCUtil;
import org.example.gdc.QueryProcessor;

public class ClientDriver {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            GDCUtil.loadGDC();
            QueryProcessor.processQuery("SELECT * FROM tweets WHERE tweet_id = 1145609117618397184");
            QueryProcessor.processQuery("SELECT * FROM users WHERE user_id = 13492622");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found");
        }
    }
}
package org.example.gdc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GDCUtil {
    private static final String GDC_FILE_PATH = "src/main/java/org/example/gdc.txt";
    private static final Map<String, String> gdc = new HashMap<>();

    /**
     * Loads the Global Database Configuration (GDC) from a file into a HashMap.
     *
     * @return The loaded GDC map containing key-value pairs from the file.
     */
    public static Map<String, String> loadGDC() {
        try (BufferedReader br = new BufferedReader(new FileReader(GDC_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("#") && !line.isEmpty()) {
                    String[] parts = line.split("->");
                    if (parts.length == 2) {
                        gdc.put(parts[0].trim(), parts[1].trim());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load GDC file: " + e.getMessage());
        }
        return gdc;
    }

    /**
     * Retrieves the database URL from the GDC map based on the provided key.
     *
     * @param key The key to retrieve the corresponding database URL from the GDC map.
     * @return The database URL associated with the provided key, or null if not found.
     */
    public static String getDatabaseUrl(String key) {
        return gdc.get(key);
    }

    /**
     * Extracts the IP address from a database URL string.
     *
     * @param dbUrl The database URL string containing the IP address.
     * @return The extracted IP address from the database URL, or null if the URL is invalid.
     */
    public static String extractIpAddress(String dbUrl) {
        if (dbUrl != null) {
            String[] parts = dbUrl.split("//")[1].split(":");
            return parts[0];
        }
        return null;
    }
}
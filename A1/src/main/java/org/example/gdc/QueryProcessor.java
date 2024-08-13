package org.example.gdc;

import static org.example.gdc.GDCUtil.extractIpAddress;
import static org.example.gdc.GDCUtil.getDatabaseUrl;

public class QueryProcessor {

    /**
     * Process a database query, determine the target fragment (tweets or users),
     * redirect to the corresponding database URL, extract the IP address, and execute the query.
     *
     * @param query The database query to be processed and executed.
     */
    public static void processQuery(String query) {
        System.out.println("\nExecuting query: " + query);
        String fragment = determineFragment(query);

        System.out.println("Determined fragment for query: " + fragment);
        String dbUrl = getDatabaseUrl(fragment);

        System.out.println("Redirecting to database URL: " + dbUrl);

        String ipAddress = extractIpAddress(dbUrl);
        System.out.println("Redirecting to IP address: " + ipAddress);
        DatabaseUtil.executeQuery(dbUrl, query);
    }

    /**
     * Determine the fragment (tweets or users) based on the provided query.
     *
     * @param query The database query to determine the fragment from.
     * @return The fragment name ("tweets", "users", or "unknown").
     */
    private static String determineFragment(String query) {
        if (query.toLowerCase().contains("tweets")) {
            return "tweets";
        } else if (query.toLowerCase().contains("users")) {
            return "users";
        } else {
            return "unknown";
        }
    }
}

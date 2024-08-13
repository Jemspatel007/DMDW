package org.example.dataimport.util;

/**
 * Utility class for converting date strings to SQL-compatible datetime format
 * and obtaining month numbers from month names.
 */
public class DataConversionUtil {

    /**
     * Converts a date string in the format "dd MMM yyyy HH:mm:ss" to SQL-compatible datetime format "yyyy-MM-dd HH:mm:ss".
     *
     * @param dateString Date string in the format "dd MMM yyyy HH:mm:ss".
     * @return Converted datetime string in the format "yyyy-MM-dd HH:mm:ss".
     */
    public static String convertToDateTime(String dateString) {
        String[] parts = dateString.split(" ");
        String month = getMonthNumber(parts[1]);
        return parts[2] + "-" + month + "-" + parts[0] + " " + parts[3];
    }

    /**
     * Returns the month number corresponding to the given month name abbreviation.
     *
     * @param monthName Month name abbreviation (e.g., "Jan" for January).
     * @return Month number as a string (e.g., "01" for January).
     * @throws IllegalArgumentException If the provided month name abbreviation is invalid.
     */
    public static String getMonthNumber(String monthName) {
        return switch (monthName) {
            case "Jan" -> "01";
            case "Feb" -> "02";
            case "Mar" -> "03";
            case "Apr" -> "04";
            case "May" -> "05";
            case "Jun" -> "06";
            case "Jul" -> "07";
            case "Aug" -> "08";
            case "Sep" -> "09";
            case "Oct" -> "10";
            case "Nov" -> "11";
            case "Dec" -> "12";
            default -> throw new IllegalArgumentException("Invalid month: " + monthName);
        };
    }
}
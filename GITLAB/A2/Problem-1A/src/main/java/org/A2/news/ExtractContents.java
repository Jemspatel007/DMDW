package org.A2.news;

import java.util.regex.Matcher;

import static org.A2.utils.Constants.*;

/**
 * Class to extract contents from text, such as title and body.
 */
public class ExtractContents {

    /**
     * Extracts the title from the given text using a regex pattern.
     *
     * @param text the text from which the title will be extracted.
     * @return the extracted title, cleaned and trimmed, or an empty string if not found.
     */
    public static String extractTitle(String text) {
        Matcher titleMatcher = TITLE_PATTERN.matcher(text);
        if (titleMatcher.find()) {
            return titleMatcher.group(1)
                    .replaceAll("(&lt;)|>|[^\\w\\s]+", "")
                    .replaceAll("\\s+", " ")
                    .trim();
        }
        return "";
    }

    /**
     * Extracts the body from the given text using a regex pattern.
     *
     * @param text the text from which the body will be extracted.
     * @return the extracted body, cleaned and trimmed, or an empty string if not found.
     */
    public static String extractBody(String text) {
        Matcher bodyMatcher = BODY_PATTERN.matcher(text);
        if (bodyMatcher.find()) {
            return bodyMatcher.group(1)
                    .replaceAll("(&lt;)|>|[^\\w\\s]+", "")
                    .replaceAll("\\s+", " ")
                    .trim();
        }
        return "";
    }
}
package org.example;

import org.apache.spark.api.java.JavaRDD;

import java.util.Arrays;
import java.util.regex.Pattern;

public class TextCleaner {
    private static final Pattern SPACE = Pattern.compile(" ");

    /**
     * Cleans and tokenizes the input lines of text.
     *
     * @param lines JavaRDD<String> containing lines of text to be processed.
     * @return JavaRDD<String> where each element is a token (word) from the input lines,
     *         with non-alphabetic characters removed and text converted to lowercase.
     */
    public static JavaRDD<String> cleanAndTokenize(JavaRDD<String> lines) {
        JavaRDD<String> cleanedLines = lines.map(line ->
                line.replaceAll("[^a-zA-Z\\s]", " ")
                        .toLowerCase().trim()
        );
        return cleanedLines.flatMap(cleanedLine -> Arrays.asList(SPACE.split(cleanedLine)).iterator());
    }
}
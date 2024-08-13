package org.example.utils;

import java.util.regex.Pattern;

/**
 * Class to store constant values used throughout the application.
 */
public class Constants {
    public static final Pattern REUTER_PATTERN = Pattern.compile("<REUTERS.*?>(.*?)</REUTERS>", Pattern.DOTALL);
    public static final Pattern TITLE_PATTERN = Pattern.compile("<TITLE>(.*?)</TITLE>", Pattern.DOTALL);
    public static final Pattern BODY_PATTERN = Pattern.compile("<BODY>(.*?)</BODY>", Pattern.DOTALL);
    public static final String FILE_PATH = "./reut2-014.sgm";
    public static final String POSITIVE_WORDS_FILEPATH = "./positive-words.txt";
    public static final String NEGATIVE_WORDS_FILEPATH = "./negative-words.txt";
    public static final String CSV_FILEPATH = "./bag-of-words.csv";
}
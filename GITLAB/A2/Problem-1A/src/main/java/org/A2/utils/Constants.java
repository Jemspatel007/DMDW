package org.A2.utils;

import java.util.regex.Pattern;

/**
 * Class to store constant values used throughout the application.
 */
public class Constants {
    public static final String MONGO_URI = "mongodb+srv://jems007patel:Jems%401205@lab6-cluster.qoacdjh.mongodb.net/?retryWrites=true&w=majority&appName=lab6-cluster";
    public static final String DATABASE_NAME = "ReuterDb";
    public static final String COLLECTION_NAME = "Articles";
    public static final Pattern REUTER_PATTERN = Pattern.compile("<REUTERS.*?>(.*?)</REUTERS>", Pattern.DOTALL);
    public static final Pattern TITLE_PATTERN = Pattern.compile("<TITLE>(.*?)</TITLE>", Pattern.DOTALL);
    public static final Pattern BODY_PATTERN = Pattern.compile("<BODY>(.*?)</BODY>", Pattern.DOTALL);
    public static final String FILE_PATH = "./reut2-014.sgm";
}
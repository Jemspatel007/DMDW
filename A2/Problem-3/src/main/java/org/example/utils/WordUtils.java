package org.example.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for operations related to words, including creating a bag of words and loading word lists from files.
 */
public class WordUtils {

    /**
     * Creates a bag of words from a given text.
     *
     * @param text the input text to be analyzed and converted into a bag of words.
     * @return a Map where the keys are words and the values are their frequencies in the text.
     */
    public static Map<String, Integer> createBagOfWords(String text) {
        Map<String, Integer> bow = new HashMap<>();
        String[] words = text.toLowerCase().split("\\s+");
        for (String word : words) {
            bow.put(word, bow.getOrDefault(word, 0) + 1);
        }
        return bow;
    }

    /**
     * Loads a list of words from a file.
     *
     * @param fileName the name of the file from which words are to be read.
     * @return a List of words read from the file, with each word converted to lowercase.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public static List<String> loadWordsFromFile(String fileName) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String word;
            while ((word = br.readLine()) != null) {
                words.add(word.trim().toLowerCase());
            }
        }
        return words;
    }
}
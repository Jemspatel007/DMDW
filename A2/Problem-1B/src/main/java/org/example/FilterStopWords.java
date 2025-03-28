package org.example;

import org.apache.spark.api.java.JavaRDD;

import java.util.Arrays;
import java.util.List;
/**
 * Utility class for filtering out stop words from a JavaRDD of words.
 */
public class FilterStopWords {

    private static final List<String> STOP_WORDS = Arrays.asList(
            "a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any", "are", "aren't", "as",
            "at", "be", "because", "been", "before", "being", "below", "between", "both", "but", "by", "can't", "cannot",
            "could", "d", "did", "didn't", "do", "does", "doesn't", "doing", "don't", "down", "during", "each", "few",
            "for", "from", "further", "had", "hadn't", "has", "hasn't", "have", "haven't", "having", "he", "her", "here",
            "hers", "herself", "him", "himself", "his", "how", "i", "if", "in", "into", "is", "isn't", "it", "it's", "its",
            "itself", "just", "ll", "m", "me", "might", "more", "most", "mustn't", "my", "myself", "need", "no", "nor",
            "not", "now", "o", "of", "off", "on", "once", "only", "or", "other", "our", "ours", "ourselves", "out",
            "over", "own", "re", "s", "same", "shan't", "she", "should", "shouldn't", "so", "t", "than", "that", "the",
            "their", "theirs", "them", "themselves", "then", "there", "these", "they", "this", "those", "through", "to",
            "too", "under", "until", "up", "ve", "very", "was", "wasn't", "we", "were", "weren't", "what", "when",
            "where", "which", "while", "who", "whom", "why", "will", "with", "won't", "would", "y", "you", "your",
            "yours", "yourself", "yourselves"
    );


    /**
     * Filters out stop words from a JavaRDD of words.
     *
     * @param words a JavaRDD containing individual words as strings.
     * @return a JavaRDD containing only those words that are not stop words and are not empty.
     */
    public static JavaRDD<String> filterStopWords(JavaRDD<String> words) {
        return words.filter(word -> !word.isEmpty() && !STOP_WORDS.contains(word));
    }
}

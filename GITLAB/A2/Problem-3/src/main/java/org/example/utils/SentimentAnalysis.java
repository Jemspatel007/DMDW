package org.example.utils;

import org.example.enums.Polarity;
import org.example.model.SentimentResult;

import java.util.List;
import java.util.Map;

/**
 * Utility class for performing sentiment analysis on text.
 */
public class SentimentAnalysis {

    /**
     * Analyzes the sentiment of a news article based on the given bag of words and sentiment word lists.
     *
     * @param newsNumber the identifier number of the news article.
     * @param title the title of the news article.
     * @param bow a map representing the bag of words with their frequencies.
     * @param positiveWords a list of positive words used for sentiment analysis.
     * @param negativeWords a list of negative words used for sentiment analysis.
     * @return a SentimentResult object containing the analysis results.
     */
    public static SentimentResult analyzeSentiment(int newsNumber, String title, Map<String, Integer> bow, List<String> positiveWords, List<String> negativeWords) {
        int score = 0;
        StringBuilder matches = new StringBuilder();

        for (String word : bow.keySet()) {
            if (positiveWords.contains(word)) {
                score += 1;
                matches.append(word).append(", ");
            } else if (negativeWords.contains(word)) {
                score -= 1;
                matches.append(word).append(", ");
            }
        }

        String polarity;
        if (score > 0) {
            polarity = String.valueOf(Polarity.POSITIVE);
        } else if (score < 0) {
            polarity = String.valueOf(Polarity.NEGATIVE);
        } else {
            polarity = String.valueOf(Polarity.NEUTRAL);
        }

        return new SentimentResult(newsNumber, title, matches.toString().trim(), score, polarity);
    }
}
package org.example.model;

/**
 * Class representing the result of a sentiment analysis on a news article.
 */
public class SentimentResult {
    private final int newsNumber;
    private final String titleContent;
    private final String match;
    private final int score;
    private final String polarity;

    /**
     * Constructor to initialize a SentimentResult with details of the sentiment analysis.
     *
     * @param newsNumber   the number identifying the news article.
     * @param titleContent the title content of the news article.
     * @param match        the matched keyword or phrase.
     * @param score        the sentiment score of the article.
     * @param polarity     the polarity of the sentiment (e.g., positive, negative, neutral).
     */
    public SentimentResult(int newsNumber, String titleContent, String match, int score, String polarity) {
        this.newsNumber = newsNumber;
        this.titleContent = titleContent;
        this.match = match;
        this.score = score;
        this.polarity = polarity;
    }

    /**
     * Converts the SentimentResult to a CSV string format.
     *
     * @return a CSV string representation of the SentimentResult.
     */
    public String toCsvString() {
        return String.format("%d,\"%s\",\"%s\",%d,%s", newsNumber, titleContent, match, score, polarity);
    }
}
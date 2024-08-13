package org.example.model;

/**
 * Class representing an article with a title and body.
 */
public class Article {
    private final String title;
    private final String body;

    /**
     * Constructor to initialize an Article with a title and body.
     *
     * @param title the title of the article.
     * @param body the body of the article.
     */
    public Article(String title, String body) {
        this.title = title;
        this.body = body;
    }

    /**
     * Gets the title of the article.
     *
     * @return the title of the article.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the body of the article.
     *
     * @return the body of the article.
     */
    public String getBody() {
        return body;
    }
}
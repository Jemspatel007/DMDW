package org.example.news;

import org.bson.Document;

import static org.example.news.ExtractContents.extractBody;
import static org.example.news.ExtractContents.extractTitle;

/**
 * Class for creating a MongoDB Document from an article's text.
 */
public class CreateArticle {

    /**
     * Creates a MongoDB Document from the given article text.
     *
     * @param articleText the text of the article.
     * @return a Document containing the title and body of the article, or null if both are empty.
     */
    public static Document createArticleDocument(String articleText) {
        String title = extractTitle(articleText);
        String body = extractBody(articleText);

        if (title.isEmpty() && body.isEmpty()) {
            return null;
        } else {
            return new Document()
                    .append("title", title)
                    .append("body", body);
        }
    }
}
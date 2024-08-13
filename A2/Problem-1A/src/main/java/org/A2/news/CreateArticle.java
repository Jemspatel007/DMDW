package org.A2.news;

import org.bson.Document;

import static org.A2.news.ExtractContents.extractBody;
import static org.A2.news.ExtractContents.extractTitle;

/**
 * Class to create MongoDB documents for articles.
 */
public class CreateArticle {

    /**
     * Creates a Document from an article text by extracting the title and body.
     *
     * @param articleText the text of the article to be processed.
     * @return a Document containing the title and body of the article, or null if both are empty.
     */
    static Document createArticleDocument(String articleText) {
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
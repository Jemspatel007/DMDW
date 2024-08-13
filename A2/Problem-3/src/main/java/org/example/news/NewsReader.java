package org.example.news;

import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.example.utils.Constants.FILE_PATH;
import static org.example.utils.Constants.REUTER_PATTERN;

/**
 * Class for reading and processing news articles from a file.
 */
public class NewsReader {

    private static final List<Document> articles = new ArrayList<>();

    /**
     * Reads articles from a news file, extracts their content, and creates a list of MongoDB Documents.
     *
     * @return a list of Documents representing the articles read from the file.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public static List<Document> readArticles() throws IOException {
        System.out.println("Reading articles from the News File......");
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            for (String currentLine = br.readLine(); currentLine != null; currentLine = br.readLine()) {
                fileContent.append(currentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        Matcher reuterMatcher = REUTER_PATTERN.matcher(fileContent.toString());

        for (boolean hasNext = reuterMatcher.find(); hasNext; hasNext = reuterMatcher.find()) {
            String articleContent = reuterMatcher.group(1);
            Document doc = CreateArticle.createArticleDocument(articleContent);
            if (doc != null) {
                articles.add(doc);
            }
        }
        return new ArrayList<>(articles);
    }
}
package org.A2.news;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;

import org.bson.Document;

import static org.A2.utils.Constants.FILE_PATH;
import static org.A2.utils.Constants.REUTER_PATTERN;

/**
 * Class to read articles from a file and convert them into MongoDB documents.
 */
public class NewsReader {

    private static final List<Document> articles = new ArrayList<>();

    /**
     * Reads articles from a file and converts them into a list of MongoDB Document objects.
     *
     * @return a list of Document objects representing the articles.
     * @throws IOException if an I/O error occurs reading from the file.
     */
    public static List<Document> readArticles() throws IOException {
        System.out.println("Reading articles from the News File");
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
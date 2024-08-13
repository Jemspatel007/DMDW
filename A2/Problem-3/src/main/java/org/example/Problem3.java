package org.example;

import org.bson.Document;
import org.example.model.SentimentResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.example.news.NewsReader.readArticles;
import static org.example.utils.Constants.*;
import static org.example.utils.ImportCSV.writeResultsToCsv;
import static org.example.utils.SentimentAnalysis.analyzeSentiment;
import static org.example.utils.WordUtils.createBagOfWords;
import static org.example.utils.WordUtils.loadWordsFromFile;

public class Problem3 {

    public static void main(String[] args) throws IOException {
        List<Document> articles = readArticles();
        List<String> positiveWords = loadWordsFromFile(POSITIVE_WORDS_FILEPATH);
        List<String> negativeWords = loadWordsFromFile(NEGATIVE_WORDS_FILEPATH);
        List<SentimentResult> results = new ArrayList<>();

        for (int i = 0; i < articles.size(); i++) {
            Document article = articles.get(i);
            String title = article.getString("title");
            Map<String, Integer> bow = createBagOfWords(title);
            SentimentResult result = analyzeSentiment(i + 1, title, bow, positiveWords, negativeWords);
            results.add(result);
        }

        writeResultsToCsv(results, CSV_FILEPATH);
    }
}
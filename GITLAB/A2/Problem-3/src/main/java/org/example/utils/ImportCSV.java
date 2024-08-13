package org.example.utils;

import org.example.model.SentimentResult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Utility class for importing sentiment analysis results and writing them to a CSV file.
 */
public class ImportCSV {

    /**
     * Writes sentiment analysis results to a CSV file.
     *
     * @param results the list of SentimentResult objects to be written to the CSV file.
     * @param fileName the name of the CSV file where the results will be saved.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    public static void writeResultsToCsv(List<SentimentResult> results, String fileName) throws IOException {
        System.out.println("Writing sentiment analysis result into CSV file......");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write("News#,Title Content,Match,Score,Polarity\n");
            for (SentimentResult result : results) {
                bw.write(result.toCsvString() + "\n");
            }
        }
        System.out.println("CSV File is created successfully......");
    }
}

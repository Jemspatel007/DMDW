package org.example.dataimport.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class for creating CSV readers using OpenCSV library.
 */
public class CSVUtils {

    /**
     * Creates a CSV reader for the given file path, skipping the header line.
     *
     * @param filePath Path to the CSV file.
     * @return CSVReader object configured to read from the specified file path.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public CSVReader createCSVReader(String filePath) throws IOException {
        return new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(1).build();
    }
}
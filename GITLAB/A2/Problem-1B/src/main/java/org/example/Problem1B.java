package org.example;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.SparkConf;
import scala.Tuple2;

import java.util.List;

import static org.example.FilterStopWords.filterStopWords;
import static org.example.WordFrequencyGetter.getHighestFrequencyWords;
import static org.example.WordFrequencyGetter.getLowestFrequencyWords;
import static org.example.WordUtils.countWords;

public class Problem1B {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("ReuterWordCount").setMaster("local[*]");
        try (JavaSparkContext sc = new JavaSparkContext(conf)) {
            String inputFile = args[0];
            JavaRDD<String> lines = sc.textFile(inputFile);

            JavaRDD<String> words = TextCleaner.cleanAndTokenize(lines);
            JavaRDD<String> filteredWords = filterStopWords(words);
            JavaPairRDD<String, Integer> wordCounts = countWords(filteredWords);
            
            List<Tuple2<String, Integer>> highestFreqencyWordsList = getHighestFrequencyWords(wordCounts);
            Tuple2<List<Tuple2<String, Integer>>, Long> lowestFreqencyWordsInfo = getLowestFrequencyWords(wordCounts, 10);

            if (!highestFreqencyWordsList.isEmpty()) {
                Tuple2<String, Integer> highestFreqWord = highestFreqencyWordsList.get(0);
                System.out.println("Word which has the Highest frequency: " + highestFreqWord._1() + " - " + highestFreqWord._2());
            } else {
                System.out.println("No words found for the highest frequency.");
            }

            if (!lowestFreqencyWordsInfo._1().isEmpty()) {
                System.out.println("Word which has the Lowest frequency (limited to 10):");
                for (Tuple2<String, Integer> word : lowestFreqencyWordsInfo._1()) {
                    System.out.println(word._1() + " - " + word._2());
                }
                System.out.println("Total number of words with frequency of " + lowestFreqencyWordsInfo._2() + ": " + lowestFreqencyWordsInfo._2());
            } else {
                System.out.println("No words found for the lowest frequency.");
            }

        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }
    }
}
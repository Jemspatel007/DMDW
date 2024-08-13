package org.example;

import org.apache.spark.api.java.JavaPairRDD;
import scala.Tuple2;

import java.util.List;

public class FrequencyCounter {
    /**
     * Returns the word with the highest frequency.
     * @param wordCounts JavaPairRDD of words and their counts.
     * @return List of Tuple2 with the highest frequency word(s).
     */
    public static List<Tuple2<String, Integer>> getHighestFrequencyWords(JavaPairRDD<String, Integer> wordCounts) {
        int highestFrequency = wordCounts.map(Tuple2::_2).max(Integer::compareTo);
        return wordCounts.filter(tuple -> tuple._2 == highestFrequency).collect();
    }

    /**
     * Returns the top N words with the lowest frequency and the total count of such words.
     * @param wordCounts JavaPairRDD of words and their counts.
     * @param topN Number of top words to return with the lowest frequency.
     * @return Tuple containing a list of Tuple2 with the lowest frequency word(s) and the total count.
     */
    public static Tuple2<List<Tuple2<String, Integer>>, Long> getLowestFrequencyWords(JavaPairRDD<String, Integer> wordCounts, int topN) {
        int lowestFrequency = wordCounts.map(Tuple2::_2).min(Integer::compareTo);
        JavaPairRDD<String, Integer> lowestFreqWords = wordCounts.filter(tuple -> tuple._2 == lowestFrequency);
        List<Tuple2<String, Integer>> lowestFreqWordsList = lowestFreqWords.take(topN);
        long totalCount = lowestFreqWords.count();
        return new Tuple2<>(lowestFreqWordsList, totalCount);
    }
}
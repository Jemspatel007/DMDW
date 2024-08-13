package org.example;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

/**
 * Utility class for processing words in a JavaRDD, including counting occurrences and sorting.
 */
public class WordUtils {

    /**
     * Counts the occurrences of each word in the filtered words RDD.
     *
     * @param filteredWords a JavaRDD containing individual words as strings, which have been filtered (e.g., stop words removed).
     * @return a JavaPairRDD where the key is the word and the value is its count.
     */
    public static JavaPairRDD<String, Integer> countWords(JavaRDD<String> filteredWords) {
        return filteredWords.mapToPair((PairFunction<String, String, Integer>) word -> new Tuple2<>(word, 1))
                .reduceByKey(Integer::sum);
    }
}
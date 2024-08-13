package org.lab5;

import java.util.Arrays;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Lab5 {
    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf().setAppName("SumOfNumbers").setMaster("local[*]");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> inputFileRDD  = sparkContext.textFile("input.txt");

        int totalSum = inputFileRDD.flatMap(line -> Arrays.asList(line.split(",")).iterator())
                .map(number -> Integer.parseInt(number))
                .reduce(Integer::sum);

        System.out.println("Total sum of the numbers: " + totalSum);

        sparkContext.stop();
    }
}
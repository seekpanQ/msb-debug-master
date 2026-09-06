package com.mashibing.debug.idea.example;

public class AverageFinder {
    public static void main(String[] args) {
        System.out.println("Average finder v0.1");
        args = new String[]{"1", "2", "3"};
        double avg = findAverage(args);
        System.out.println("The average is " + avg);
    }

    private static double findAverage(String[] input) {
        double result = 0;
        for (String s : input) {
            result += Integer.parseInt(s);
        }
        return result;
    }
}

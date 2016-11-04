package com.java8.forkJoin.study;

import static com.java8.stream.study.measurePerfProvider.measurePerf;

/**
 * Created by Lingfeng on 2016/7/28.
 */
public class ForkJoinTest {

    public static void main(String... args) {
        System.out.println("ForkJoin sum done in: " + measurePerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000) + " msecs");
    }
}

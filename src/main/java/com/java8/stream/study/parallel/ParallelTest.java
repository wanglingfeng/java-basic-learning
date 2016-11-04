package com.java8.stream.study.parallel;

import static com.java8.stream.study.measurePerfProvider.measurePerf;

/**
 * Created by Lingfeng on 2016/7/27.
 */
public class ParallelTest {

    public static void main(String... args) {
        System.out.println("Sequential sum done in: " + measurePerf(ParallelStreams::sequentialSum, 10_000_000) + " msecs");
        System.out.println("Iterative sum done in: " + measurePerf(ParallelStreams::iterativeSum, 10_000_000) + " msecs");
        System.out.println("Parallel sum done in: " + measurePerf(ParallelStreams::parallelSum, 10_000_000) + " msecs");
        System.out.println("Ranged sum done in: " + measurePerf(ParallelStreams::rangedSum, 10_000_000) + " msecs");
    }
}

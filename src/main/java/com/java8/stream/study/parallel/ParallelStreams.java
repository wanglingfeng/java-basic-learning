package com.java8.stream.study.parallel;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by Lingfeng on 2016/7/27.
 */
public class ParallelStreams {

    // 顺序流处理
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    // java7处理
    public static long iterativeSum(long n) {
        long result = 0;

        for (long i = 1L; i <= n; i++) {
            result += i;
        }

        return result;
    }

    // 并行流处理
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }
}

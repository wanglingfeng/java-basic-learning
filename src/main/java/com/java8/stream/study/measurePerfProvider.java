package com.java8.stream.study;

import java.util.function.Function;

/**
 * Created by Lingfeng on 2016/7/28.
 */
public class measurePerfProvider {

    public static long measurePerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;

            /*System.out.println("Result: " + sum);*/
            if (duration < fastest) fastest = duration;
        }

        return fastest;
    }
}

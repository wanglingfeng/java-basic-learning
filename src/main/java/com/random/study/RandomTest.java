package com.random.study;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.String.join;

/**
 * Created by Lingfeng on 2016/7/14.
 */
public class RandomTest {

    public static char[] seeds = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static void main(String[] args) {
        int max = 999;
        int min = 100;

        /*for (int i = 0; i < 100; i++) {
            System.out.println(getRandom(max, min));
        }*/

        masurePerf(RandomTest::randomPassword);
    }

    public static int getRandom(int max, int min) {
        Random random = new Random();

        return random.nextInt(max) % (max - min + 1) + min;
    }

    public static String randomPassword() {
        return IntStream.rangeClosed(0, 5)
                .mapToObj(i -> String.valueOf(seeds[new Random().nextInt(seeds.length)]))
                .collect(Collectors.joining());
    }

    public static void masurePerf(Supplier<String> supplier) {
        long fastest = Long.MAX_VALUE;

        long start = System.nanoTime();

        for (int i = 0; i < 10; i++) {
            System.out.println(supplier.get());
        }

        long duration = (System.nanoTime() - start) / 1_000_000;

        if (duration < fastest) fastest = duration;

        System.out.println("random password min seconds: " + fastest);
    }
}

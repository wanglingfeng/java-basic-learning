package com.java8.stream.study;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 斐波那契数列测试
 *
 * Created by Lingfeng on 2016/7/27.
 */
public class FibonacciTest {

    public static void main(String... args) {
        fibonacciArray();

        System.out.println("--------------");

        fibonacciNumber();
    }

    /**
     * 斐波那契元组：(0, 1), (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21), ...
     */
    public static void fibonacciArray() {
        Stream.iterate(new int[] {0, 1}, t -> new int[] {t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));
    }

    /**
     * 斐波那契数列：1, 1, 2, 3, 5, 8, 13, 21, ...
     */
    public static void fibonacciNumber() {
        IntStream.generate(new IntSupplier() {

            private int current = 0;
            private int next = 1;

            @Override
            public int getAsInt() {
                int nowCurrent = this.current;
                int nextValue = this.next + this.current;
                this.current = this.next;
                this.next = nextValue;

                return nowCurrent;
            }
        }).limit(10).forEach(System.out::println);
    }
}

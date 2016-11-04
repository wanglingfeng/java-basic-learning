package com.java8.stream.study;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * peek方法测试
 * Created by Lingfeng on 2016/7/28.
 */
public class PeekTest {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);

        List<Integer> result = numbers.stream()
                .peek(x -> System.out.println("from stream: " + x))
                .map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter: " + x))
                .limit(3)
                .peek(x -> System.out.println("after limit: " + x))
                .collect(toList());

        System.out.println("------");
        System.out.println(result);
    }
}

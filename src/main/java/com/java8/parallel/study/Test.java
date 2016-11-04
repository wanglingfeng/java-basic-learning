package com.java8.parallel.study;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Lingfeng on 2016/8/1.
 */
public class Test {

    public static void main(String... args) {
        Stream<String> strStream = Arrays.asList("a", "asd", "c", "def").stream();

        Results results = new StreamForker<>(strStream)
                .fork("startWith", stream -> stream.anyMatch(str -> str.startsWith("a")))
                .fork("length", stream -> stream.mapToInt(String::length).sum())
                .fork("filter", stream -> stream.filter(s -> s.length() > 1).collect(Collectors.toList()))
                .getResults();

        boolean isStartWith = results.get("startWith");
        int length = results.get("length");
        List<String> filter = results.get("filter");

        System.out.println("isStartWith: " + isStartWith);
        System.out.println("length: " + length);
        System.out.println("filter: " + filter);
    }
}

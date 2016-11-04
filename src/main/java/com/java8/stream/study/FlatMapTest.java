package com.java8.stream.study;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Lingfeng on 2016/7/26.
 */
public class FlatMapTest {

    public static void main(String... args) {
        List<String> words = Arrays.asList("Hello", "World");

        List<String> uniqueCharacters = words.stream()
                .map(word -> word.split("")) // 将每个单词转换为由其字母构成的数组
                .flatMap(Arrays::stream) // 将各个生成流扁平化为单个流,Arrays::stream将String[]流转化为String流;
                                         // 通过flatMap将各个独立的流连接起来成为一个流
                .distinct()
                .collect(Collectors.toList());

        System.out.println(uniqueCharacters);

        List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
        int product = numbers.stream().reduce(0, Integer::sum); // 数组元素求和

        System.out.println(product);
    }
}
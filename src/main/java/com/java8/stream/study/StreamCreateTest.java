package com.java8.stream.study;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Lingfeng on 2016/7/27.
 */
public class StreamCreateTest {

    public static void main(String... args) {
        // 由值创建流
        Stream<String> stringStream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stringStream.map(String::toUpperCase).forEach(System.out::println);

        // 创建空流
        Stream<String> empty = Stream.empty();

        // 由数组创建流
        int[] numbers ={2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();

        // 由文件生成流
        // 查看文件有多少个不相同的词
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines
                    .flatMap(line -> Arrays.stream(line.split(" "))) // 生成单词流
                    .distinct()
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建无限流
        Stream.iterate(0, n -> n + 2)
                .limit(10) // 对流加以限制，以避免打印无穷多个值
                .forEach(System.out::println);
    }
}

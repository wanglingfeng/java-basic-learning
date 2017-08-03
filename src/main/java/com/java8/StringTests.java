package com.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lfwang on 2017/8/3.
 */
public class StringTests {
    
    public static void main(String... args) {
        List<String> list = Arrays.asList("a", "b", "d", "c", "x");
        
        String result = list.stream().collect(Collectors.joining(","));

        System.out.println(result);
    }
}

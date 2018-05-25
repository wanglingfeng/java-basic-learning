package com.java8.stream.study;


import java.util.Arrays;
import java.util.List;

/**
 * @author keleguo
 * @date Created in 2018/5/24
 */
public class ListReturnTests {

    public static final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    public static void main(String[] args) {
        System.out.println(list);
        System.out.println();

        list.forEach(i -> {
            if (i == 3) return;

            System.out.println(i);
        });
    }
}

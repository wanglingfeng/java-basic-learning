package com.java8.stream.study;

import java.util.Optional;

/**
 * Created by Lingfeng on 2016/7/29.
 */
public class OptionalUtility {

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}

package com.collaborativeFiltering.study;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lfwang on 2017/5/8.
 */
public class DateStore {

    public static Map<String, Map<String, Integer>> init() {
        Map<String, Map<String, Integer>> userMap = new HashMap<>();

        Map<String, Integer> user1 = new HashMap<>();
        user1.put("A", 3);
        user1.put("B", 4);
        user1.put("C", 3);
        user1.put("D", 5);
        user1.put("E", 1);
        user1.put("F", 4);
        userMap.put("p1", user1);
        Map<String, Integer> user2 = new HashMap<>();
        user2.put("A", 2);
        user2.put("B", 4);
        user2.put("C", 4);
        user2.put("D", 5);
        user2.put("E", 3);
        user2.put("F", 2);
        userMap.put("p2", user2);
        Map<String, Integer> user3 = new HashMap<>();
        user3.put("A", 3);
        user3.put("B", 5);
        user3.put("C", 4);
        user3.put("D", 5);
        user3.put("E", 2);
        user3.put("F", 1);
        userMap.put("p3", user3);
        Map<String, Integer> user4 = new HashMap<>();
        user4.put("A", 2);
        user4.put("B", 2);
        user4.put("C", 3);
        user4.put("D", 4);
        user4.put("E", 3);
        user4.put("F", 2);
        userMap.put("p4", user4);
        Map<String, Integer> user5 = new HashMap<>();
        user5.put("A", 4);
        user5.put("B", 4);
        user5.put("C", 4);
        user5.put("D", 5);
        user5.put("E", 1);
        user5.put("F", 0);
        userMap.put("p5", user5);

        return userMap;
    }
}

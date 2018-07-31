package com.java8.stream.study;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author keleguo
 * @date Created in 2018/7/31
 */
public class PerformTest {

    public static void main(String[] args) {
        // 预热
        List<User> tmpList = createUsers(1);
        testFori(tmpList);
        testForeach(tmpList);
        testJava8ForEach(tmpList);

        List<Integer> list = Arrays.asList(10, 50, 250, 1000, 2000, 3000, 5000, 10000, 20000);
        for (int i = 0; i < list.size(); i++) {
            test(list.get(i));
        }
    }

    public static void test(int size) {
        System.out.println("-----------次数:" + size + "------------");
        List<User> list = createUsers(size);
        long nanoTime = System.nanoTime();
        testFori(list);
        long nanoTime1 = System.nanoTime();
        testForeach(list);
        long nanoTime2 = System.nanoTime();
        testJava8ForEach(list);
        long nanoTime3 = System.nanoTime();

        System.out.println("fori\t\t\t\t" + (nanoTime1 - nanoTime) / 1000.0 + " ms");
        System.out.println("增强for\t\t\t\t" + (nanoTime2 - nanoTime1) / 1000.0 + " ms");
        System.out.println("java8 foreach\t\t" + (nanoTime3 - nanoTime2) / 1000.0 + " ms");
        System.out.println();
    }

    /**
     * 初始化list
     *
     * @param size int
     * @return list
     */
    public static List<User> createUsers(int size) {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new User("dog " + (i + 1), i + 1));
        }
        return list;
    }

    /**
     * 测试fori
     *
     * @param list List
     */
    public static void testFori(List<User> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getCode();
        }
    }

    /**
     * 测试增强for循环
     *
     * @param list List
     */
    private static void testForeach(List<User> list) {
        for (User user : list) {
            user.getCode();
        }
    }

    /**
     * 测试java8的foreach
     *
     * @param list List
     */
    private static void testJava8ForEach(List<User> list) {
        list.forEach(User::getCode);
    }

    /**
     * 测试实体类
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private String name;
        private int age;

        public String getCode() {
            return "NAME:" + name + "|AGE:" + age;
        }
    }
}
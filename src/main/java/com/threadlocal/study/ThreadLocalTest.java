package com.threadlocal.study;

/**
 * Created by lfwang on 2017/2/21.
 */
public class ThreadLocalTest {

    public static void main(String... args) {
        MyThreadLocal.create(2);

        int value = MyThreadLocal.instance().getValue();
        System.out.println(value);

        MyThreadLocal.clear();
    }
}

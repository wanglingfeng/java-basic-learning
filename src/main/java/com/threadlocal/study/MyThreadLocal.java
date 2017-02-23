package com.threadlocal.study;

/**
 * 单例ThreadLocal实现
 *
 * Created by lfwang on 2017/2/23.
 */
public class MyThreadLocal {

    private static ThreadLocal<MyThreadLocal> threadLocal = new ThreadLocal<>();

    private int value;

    private MyThreadLocal() {}

    private MyThreadLocal(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static void create(int value) {
        MyThreadLocal myThreadLocal = new MyThreadLocal(value);
        threadLocal.set(myThreadLocal);
    }

    public static MyThreadLocal instance() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }
}

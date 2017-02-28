package com.reference.study.bean;

/**
 * Created by lfwang on 2017/2/21.
 */
public class A {

    public String str = null;

    public void finalize() {
        System.out.println("Finalizing ... "+ str);
    }
}

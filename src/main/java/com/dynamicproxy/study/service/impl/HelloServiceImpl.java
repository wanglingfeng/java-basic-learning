package com.dynamicproxy.study.service.impl;

import com.dynamicproxy.study.service.HelloService;

/**
 * Created by lfwang on 2017/2/28.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String world) {
        System.out.println("hello, " + world);
    }
}

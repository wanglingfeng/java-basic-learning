package com.rabbitmq.study.spring;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Lingfeng on 2016/3/1.
 */
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received < " + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return this.latch;
    }
}

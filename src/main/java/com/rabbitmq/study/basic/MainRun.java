package com.rabbitmq.study.basic;

import java.util.HashMap;

/**
 * Created by Lingfeng on 2016/2/29.
 */
public class MainRun {

    public MainRun() throws Exception {
        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Producer producer = new Producer("queue");

        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> message = new HashMap<>();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number " + i + " sent.");
        }
    }

    public static void main(String[] args) throws Exception {
        new MainRun();
    }
}

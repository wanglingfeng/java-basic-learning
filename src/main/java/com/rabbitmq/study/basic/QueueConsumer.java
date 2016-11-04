package com.rabbitmq.study.basic;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 读取队列的程序端，实现了Runnable接口。
 *
 * Created by Lingfeng on 2016/2/29.
 */
public class QueueConsumer extends EndPoint implements Runnable, Consumer {

    public QueueConsumer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    public void run() {
        try {
            // start consuming messages. Auto acknowledge messages.
            channel.basicConsume(endPointName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer " + consumerTag + " registered");
    }

    public void handleCancelOk(String consumerTag) {

    }

    public void handleCancel(String consumerTag) throws IOException {

    }

    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

    }

    public void handleRecoverOk(String consumerTag) {

    }

    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        Map map = (HashMap) SerializationUtils.deserialize(body);
        System.out.println("Message Number " + map.get("message number") + " received.");
    }
}

package com.rabbitmq.study.basic;

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * 生产者（向队列里写一条消息）
 *
 * Created by Lingfeng on 2016/2/29.
 */
public class Producer extends EndPoint {

    public Producer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("", endPointName, null, SerializationUtils.serialize(object));
    }
}

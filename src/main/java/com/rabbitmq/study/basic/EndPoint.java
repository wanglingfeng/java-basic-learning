package com.rabbitmq.study.basic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 将生产者和消费者统一为EndPoint类型的队列。不管是生产者还是消费者，连接队列的代码都是一样的
 *
 * Created by Lingfeng on 2016/2/29.
 */
public abstract class EndPoint {

    protected Channel channel;
    protected Connection connection;
    protected String endPointName;

    public EndPoint(String endPointName) throws IOException, TimeoutException {
        this.endPointName = endPointName;

        // Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();

        // hostname of your rabbitmq server
        factory.setHost("localhost");

        // getting a connection
        connection = factory.newConnection();

        // creating a channel
        channel = connection.createChannel();

        // declaring a queue for this channel. If queue does not exist,
        // it will be created on the server.
        channel.queueDeclare(endPointName, false, false, false, null);
    }

    /**
     * 关闭channel和connection。并非必须，因为隐含是自动调用的。
     *
     * @throws IOException
     * @throws TimeoutException
     */
    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }
}

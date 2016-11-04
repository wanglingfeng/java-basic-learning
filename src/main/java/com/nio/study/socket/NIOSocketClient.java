package com.nio.study.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Lingfeng on 2016/6/8.
 */
public class NIOSocketClient {

    public static void client() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;

        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 8080));

            if (socketChannel.finishConnect()) {
                int i = 1;

                while (true) {
                    Thread.sleep(1000);

                    String info = "I'm " + i++ + "-th information from client";

                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();

                    while (buffer.hasRemaining()) {
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (null != socketChannel) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        client();
    }
}

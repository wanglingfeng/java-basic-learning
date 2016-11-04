package com.aio.study;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by lfwang on 2016/10/11.
 */
public class AFCDemo {

    public static Thread current;

    public static void main(String[] args) throws Exception {
        Path filePath = Paths.get("D://tempFile/cookies");

        AsynchronousFileChannel afc = AsynchronousFileChannel.open(filePath);
        ByteBuffer byteBuffer = ByteBuffer.allocate(16 * 1024);

        //使用FutureDemo时，请注释掉completionHandlerDemo，反之亦然
        //completionHandlerDemo(afc, byteBuffer);
        futureDemo(afc, byteBuffer);

        System.out.println("Over");
    }

    private static void completionHandlerDemo(AsynchronousFileChannel afc, ByteBuffer byteBuffer) throws Exception {
        current = Thread.currentThread();

        afc.read(byteBuffer, 0, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("Bytes Read: " + result);
                current.interrupt();
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println(exc.getCause().toString());
                current.interrupt();
            }
        });

        System.out.println("Waiting for completion...");
        current.join();

        System.out.println("End");

        afc.close();
    }

    private static void futureDemo(AsynchronousFileChannel afc, ByteBuffer byteBuffer) throws Exception {
        Future<Integer> result = afc.read(byteBuffer, 0);

        while (!result.isDone()) {
            System.out.println("Waiting file channel finished...");
            Thread.sleep(1);
        }

        System.out.println("Finished? = " + result.isDone());
        System.out.println("byteBuffer = " + result.get());
        System.out.println(byteBuffer);
        afc.close();
    }
}

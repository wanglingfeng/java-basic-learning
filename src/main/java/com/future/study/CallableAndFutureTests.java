package com.future.study;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by lfwang on 2017/9/18.
 */
public class CallableAndFutureTests {
    
    public static void main(String... args) throws InterruptedException, ExecutionException {
        Callable<Integer> callable = () -> new Random().nextInt(100);
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        
        new Thread(futureTask).start();
        
        Thread.sleep(5000);
        System.out.println(futureTask.get());
    }
}

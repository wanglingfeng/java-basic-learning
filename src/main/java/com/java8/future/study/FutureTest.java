package com.java8.future.study;

import java.util.concurrent.*;

/**
 * 异步计算
 * Created by Lingfeng on 2016/7/29.
 */
public class FutureTest {

    public static void main(String... args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomeLongComputation();
            }
        });

        doSomethingElse();

        try {
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace(); // 当前线程在等待过程中被中断
        } catch (ExecutionException e) {
            e.printStackTrace(); // 在Future对象完成之前操作已过期
        } catch (TimeoutException e) {
            e.printStackTrace(); // 计算抛出一个异常
        }
    }

    public static Double doSomeLongComputation() {
        return 0.0;
    }

    public static void doSomethingElse() {

    }
}

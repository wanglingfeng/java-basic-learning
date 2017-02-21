package com.callableandfuture.study;

import java.util.concurrent.*;

/**
 * Callable和Future接口
 * Callable是类似于Runnable的接口，实现Callable接口的类和实现Runnable的类都是可被其它线程执行的任务。
 * Callable和Runnable有以下不同：
 * 1. Callable规定的方法是call()，而Runnable规定的方法是run()。
 * 2. Callable的任务执行后可返回值，而Runnable任务不能返回值。
 * 3. call()方法可抛出异常，而run()方法不能抛出异常。
 * 4. 运行Callable任务可拿到一个Future对象。
 *
 * Future表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并检索计算结果。
 * 通过Future对象可了解任务执行情况，可取消任务执行，还可获得任务执行的结果。
 *
 * Created by lfwang on 2017/2/21.
 */
public class CallableAndFutureTest {

    public static void main(String... args) {
        // 定义3个Callable类型的任务
        MyCallable task1 = new MyCallable(0);
        MyCallable task2 = new MyCallable(1);
        MyCallable task3 = new MyCallable(2);

        // 创建一个执行任务的服务
        ExecutorService es = Executors.newFixedThreadPool(3);

        try {
            // 提交并执行任务，任务启动时返回了一个Future对象，
            // 如果想得到任务执行的结果或是异常，可对这个Future对象进行操作
            Future<String> future1 = es.submit(task1);
            // 获得第一个任务的结果，如果调用get方法，当前线程会等待任务执行完毕后才往下执行
            System.out.println("task1: " + future1.get());

            Future<String> future2 = es.submit(task2);
            // 等待5秒后，再停止第二个任务。因为第二个任务进行的是无限循环
            Thread.sleep(5000);
            System.out.println("task2 cancel: " + future2.cancel(true));

            // 过去第三个任务，因为执行第三个任务会引起异常，所以下面语句将抛出异常
            Future<String> future3 = es.submit(task3);
            System.out.println("task3: " + future3.get());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        es.shutdown();
    }


    public static class MyCallable implements Callable<String> {
        private int flag = 0;

        public MyCallable(int flag) {
            this.flag = flag;
        }

        @Override
        public String call() throws Exception {
            if (0 == this.flag) {
                return "flag = 0";
            }
            if (1 == this.flag) {
                while (true) {
                    System.out.println("looping.");
                    Thread.sleep(2000);
                }
            } else {
                throw new Exception("Bad flag value!");
            }
        }
    }
}

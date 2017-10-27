package com.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 五个人只要都跑到终点了，大家可以喝啤酒。但是，只要有一个人没到终点，就不能喝
 * 
 * 利用栅栏，可以使线程相互等待，直到所有线程都到达某一点，然后栅栏将打开，所有线程将通过栅栏继续执行。
 * CyclicBarrier支持一个可选的 Runnable 参数，当线程通过栅栏时，runnable对象将被调用。
 * 构造函数CyclicBarrier(int parties, Runnable barrierAction)，
 * 当线程在CyclicBarrier对象上调用await()方法时，栅栏的计数器将增加1，当计数器为parties时，栅栏将打开。
 * 
 * 闭锁用于所有线程等待一个外部事件的发生；
 * 栅栏则是所有线程相互等待，直到所有线程都到达某一点时才打开栅栏，然后线程可以继续执行。
 * 
 * Created by lfwang on 2017/10/26.
 */
public class BeerUseCyclicBarrier {
    
    public static void main(String... args) {
        final int count = 5;
        final CyclicBarrier barrier = new CyclicBarrier(count, () -> System.out.println("drink beer."));
        
        for (int i = 0; i < count; i++) {
            new Thread(new Worker(i, barrier)).start();
        }
    }
    
    static class Worker implements Runnable {

        final int id;
        final CyclicBarrier barrier;
        
        public Worker(int id, CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }
        
        @Override
        public void run() {
            try {
                System.out.println(this.id + " start to run!");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println(this.id + "arrived.");
                this.barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}

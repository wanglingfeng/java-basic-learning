package com.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 有五个人，一个裁判。这五个人同时跑，裁判开始计时，五个人都到终点了，
 * 裁判喊停，然后统计这五个人从开始跑到最后一个撞线用了多长时间
 * 
 * 闭锁CountDownLatch唯一的构造方法CountDownLatch(int count)，
 * 当在闭锁上调用countDown()方法时，闭锁的计数器将减1，
 * 当闭锁计数器为0时，闭锁将打开，所有线程将通过闭锁开始执行
 * 
 * CountDownLatch最重要的方法
 *     countDown(): 倒数一次
 *     await(): 等待倒数到0，如果没有到达0，就只有阻塞等待了
 * 
 * 闭锁用于所有线程等待一个外部事件的发生；
 * 栅栏则是所有线程相互等待，直到所有线程都到达某一点时才打开栅栏，然后线程可以继续执行。
 * 
 * Created by lfwang on 2017/10/26.
 */
public class RaceUseCountDownLatch {
    
    public static void main(String... args) {
        final int num = 5;
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(num);
        
        for (int i = 0; i < num; i++) {
            new Thread(new AWorker(i, begin, end)).start();
        }

        try {
            Thread.sleep((long) (Math.random() * 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("judge say: run!");
        begin.countDown();
        
        long startTime = System.currentTimeMillis();

        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();

            System.out.println("judge say: all arrived!");
            System.out.println("spend time: " + (endTime - startTime));
        }

    }
    
    static class AWorker implements Runnable {

        final int id;
        final CountDownLatch begin;
        final CountDownLatch end;
        
        public AWorker(int id , CountDownLatch begin, CountDownLatch end) {
            this.id = id;
            this.begin = begin;
            this.end = end;
        }
        
        @Override
        public void run() {
            System.out.println(this.id + " ready!");
            try {
                begin.await();
                Thread.sleep((long) (Math.random() * 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(this.id + " arrived!");
                end.countDown();
            }
        }
    }
}

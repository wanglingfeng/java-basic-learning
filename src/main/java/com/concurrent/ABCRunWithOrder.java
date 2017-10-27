package com.concurrent;

/**
 * Created by lfwang on 2017/10/27.
 */
public class ABCRunWithOrder {
    
    public static void main(String... args) throws InterruptedException {
        Thread t1 = new Thread(new T1());
        Thread t2 = new Thread(new T2());
        Thread t3 = new Thread(new T3());
        
        t1.start();
        t1.join();
        
        t2.start();
        t2.join();
        
        t3.start();
        t3.join();
    }
    
    static class T1 implements Runnable {

        @Override
        public void run() {
            System.out.println("this is T1");
        }
    }

    static class T2 implements Runnable {

        @Override
        public void run() {
            System.out.println("this is T2");
        }
    }

    static class T3 implements Runnable {

        @Override
        public void run() {
            System.out.println("this is T3");
        }
    }
}

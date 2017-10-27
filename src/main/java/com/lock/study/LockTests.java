package com.lock.study;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lfwang on 2017/10/26.
 */
public class LockTests {
    
    public static void main(String... args) {
        final Outputter output = new Outputter();
        
        new Thread() {
            @Override
            public void run() {
                output.output("joseph");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                output.output("wang");
            }
        }.start();
    }

    /**
     * 为了保证锁最终被释放(发生异常情况)，要把互斥区放在try内，释放锁放在finally内
     */
    static class Outputter {
        private Lock lock = new ReentrantLock(); // 锁对象
        
        public void output(String name) {
            lock.lock(); // 得到锁
            
            try {
                for (int i = 0; i < name.length(); i++) {
                    System.out.print(name.charAt(i) + " ");
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }
    }
}

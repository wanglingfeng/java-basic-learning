package com.lock.study;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by lfwang on 2017/10/26.
 */
public class ReadWriteLockTests {
    
    public static void main(String... args) {
        final Data data = new Data();
        
        for (int i = 0; i < 3; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.set(new Random().nextInt(30));
                    }
                }
            }.start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.get();
                    }
                }
            }.start();
        }
    }
    
    static class Data {
        
        private ReadWriteLock rwLock = new ReentrantReadWriteLock();
        private int data; // 共享数据
        
        public void set(int data) {
            rwLock.writeLock().lock(); // 取到写锁

            try {
                System.out.println(Thread.currentThread().getName() + " 准备写入数据。");
                
                Thread.sleep(20);

                this.data = data;
                System.out.println(Thread.currentThread().getName() + " 写入 " + this.data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rwLock.writeLock().unlock(); // 释放写锁
            }
        }
        
        public void get() {
            rwLock.readLock().lock(); // 取到读锁
            
            try {
                System.out.println(Thread.currentThread().getName() + " 准备读取数据。");
                
                Thread.sleep(20);

                System.out.println(Thread.currentThread().getName() + " 读取 " + this.data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rwLock.readLock().unlock(); // 释放读锁
            }
        }
    }
}

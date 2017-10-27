package com.concurrent;

/**
 * Created by lfwang on 2017/10/26.
 */
public class JoinTest {
    
    public static void main(String... args) {
        Thread t1 = new Thread(new RunnableA("t1"));
        
        try {
            t1.start();
            t1.join();

            System.out.println(Thread.currentThread().getName() + " finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    static class RunnableA implements Runnable {

        private String name;
        
        public RunnableA(String name) {
            this.name = name;
        }
        
        @Override
        public void run() {
            System.out.println(this.name + " start");
            
            // 延时操作
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(this.name + " finish");
        }
    } 
}

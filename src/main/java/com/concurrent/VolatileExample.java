package com.concurrent;

/**
 * Created by lfwang on 2017/10/26.
 */
public class VolatileExample {
    
    public static void main(String... args) throws InterruptedException {
        final Run run = new Run();
        
        Thread writeThread = new Thread() {
            @Override
            public void run() {
                run.write();
            }
        };
        Thread readThread = new Thread() {
            @Override
            public void run() {
                run.read();
            }
        };
        
        writeThread.start();
        readThread.start();
        writeThread.join();
        readThread.join();
    }
    
    static class Run {
        int x = 0;
        volatile int b = 0;
        
        public void write() {
            x = 5;
            b = 1;
        }
        
        public void read() {
            int dummy = b;
            System.out.println(dummy);
            while (x != 5) {
                System.out.println("x no equals 5");
            }
        }
    }
}

package com.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 停车场只有三个车位，一开始三个车位都是空的。这时如果同时来了三辆车，看门人允许其中它们进入进入，然后放下车拦。
 * 以后来的车必须在入口等待，直到停车场中有车辆离开，打开车拦，放入一辆，如果又离开一辆，则又可以放入一辆，如此往复
 * 
 * Created by lfwang on 2017/10/27.
 */
public class SemaphoreTests {
    
    private static Semaphore park = new Semaphore(3); // 停车场只有三个车位
    
    public static void main(String... args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Car("a"));
        service.submit(new Car("b"));
        service.submit(new Car("c"));
        service.submit(new Car("d"));
        service.submit(new Car("e"));
        service.submit(new Car("f"));
        service.shutdown();
    }
    
    static class Car implements Runnable {

        private String id;
        
        Car(String id) {
            this.id = id;
        }
        
        @Override
        public void run() {
            try {
                park.acquire();
                System.out.println("car: " + this.id + " is park in");
                Thread.sleep(new Random().nextInt(1000));
                System.out.println("car: " + this.id + " is drive away");
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                park.release();
            }
        }
    }
}

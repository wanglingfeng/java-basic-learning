package com.java8.future.study;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by Lingfeng on 2016/7/29.
 */
public class Shop {

    public static void main(String... args) {
        // 使用异步API
        Shop shop = new Shop();

        long start = System.nanoTime();
        // 查询商品，试图取得商品的价格
        Future<Double> futurePrice = shop.getPriceAsync("my favourite product");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        // 执行更多任务，比如查询其他商店。在计算商品价格的同时
        doSomethingElse();

        try {
            // 从Future对象中读取价格，如果价格未知，会发生阻塞
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Price returned after " + retrievalTime + "msecs");
    }

    public static void doSomethingElse() {

    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price); // 如果价格计算正常结束，完成Future操作并设置商品价格
            } catch (Exception e) {
                futurePrice.completeExceptionally(e); // 否则就抛出导致失败的异常，完成这次Future操作
            }

        }).start();

        return futurePrice;
    }

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.exchange;

/**
 * Created by lfwang on 2017/10/17.
 */
public class ExchangeTests {
    
    public static void main(String... args) {
        int x = 5;
        int y = 123;

        System.out.println("x: " + x + " & y: " + y);

        exchange1(x, y);

        exchange2(x, y);        
    }
    
    private static void exchange1(int x, int y) {
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;

        System.out.println("after exchange");
        System.out.println("x: " + x + " & y: " + y);
    }

    private static void exchange2(int x, int y) {
        x = x + y;
        y = x - y;
        x = x - y;

        System.out.println("after exchange");
        System.out.println("x: " + x + " & y: " + y);
    }
}

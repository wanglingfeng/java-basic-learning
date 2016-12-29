package com;

/**
 * Created by lfwang on 2016/11/23.
 */
public class LoopTest {

    public static void main(String... args) {
        loopPrint();
    }

    public static void loopPrint() {
        int i = 1;

        while (true) {
            if (i > 10) {
                return;
            } else {
                System.out.println(i++);
            }
        }
    }
}

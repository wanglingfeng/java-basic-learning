package com.math.study;

/**
 * Created by lfwang on 2017/1/3.
 */
public class MathTest {

    public static void main(String... args) {
        //int i = 1 << 4; // 结果等于1 * 2的4次方

        int i = 0;
        i = i++; // 因为这里将自增前i的值，重新赋值给了i，所以i任然等于0
        System.out.println(i);

        System.out.println(Integer.toBinaryString(25));
    }
}

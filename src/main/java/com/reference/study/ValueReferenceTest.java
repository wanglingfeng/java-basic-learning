package com.reference.study;

/**
 * 值引用
 *
 * Created by lfwang on 2017/2/26.
 */
public class ValueReferenceTest {

    public static void main(String... args) {
        int a = 3;
        int b = 4;

        change(a, b);

        System.out.println("a = " + a + " , b = " + b);

        System.out.println("-----------");

        int[] counts = {1, 2, 3, 4, 5};
        changeArray(counts);
        System.out.println("counts[0] = " + counts[0]);
    }

    /**
     * 在函数中传递基本数据类型
     * 参数中传递的是 基本类型 a 和 b 的拷贝,在函数中交换的也是那份拷贝的值 而不是数据本身
     * @param i
     * @param j
     */
    public static void change(int i, int j) {
        int temp = i;
        i = j;
        j = temp;
    }

    /**
     * 传的是引用数据类型
     * 在方法中 传递引用数据类型 int 数组，实际上传递的是其引用count的拷贝，他们都指向数组对象，
     * 在方法中可以改变数组对象的内容。即:对复制的引用所调用的方法更改的是同一个对象。
     * @param counts
     */
    public static void changeArray(int[] counts) {
        counts[0] = 6;
    }
}

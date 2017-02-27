package com.reference.study;

import java.util.Arrays;

/**
 * Java参数，不管是原始类型还是引用类型，传递的都是副本
 *
 * 对于对象类型，也就是Object的子类，如果你在方法中修改了它的成员的值，那个修改是生效的，方法调用结束后，
 * 它的成员是新的值，但是如果你把它指向一个其它的对象，方法调用结束后，原来对它的引用并没用指向新的对象。
 *
 * Created by lfwang on 2017/2/26.
 */
public class ObjectReferenceTest {

    public static void main(String... args) {
        A a = new A();

        addNew(a);
        System.out.println("a.i = " + a.i);

        System.out.println("-----------");

        add(a);
        System.out.println("a.i = " + a.i);

        System.out.println("-----------");

        Example example = new Example();
        changeStringAndArray(example.str, example.ch);
        System.out.println(example.str + " and " + Arrays.toString(example.ch));
    }

    /**
     * 在该程序中，对象的引用指向的是A ,而在change方法中，传递的引用的一份副本则指向了一个新的A，并对其进行操作。
     * 而原来的A对象并没有发生任何变化。 引用指向的是还是原来的A对象。
     * @param a
     */
    public static void addNew(A a) {
        a = new A();
        a.i++;
    }

    /**
     * 向add方法传入的是A的拷贝，但都指向同一个i，
     * 在方法中可以改变数组对象的内容。即:对复制的引用所调用的方法更改的是同一个对象。
     * @param a
     */
    public static void add(A a) {
        a.i++;
    }

    /**
     * String 比较特别，看过String 代码的都知道， String 是  final 的。所以值是不变的。 函数中String对象引用
     * 的副本指向了另外一个新String对象,而数组对象引用的副本没有改变,而是改变对象中数据的内容.
     * @param str
     * @param ch
     */
    public static void changeStringAndArray(String str, char[] ch) {
        str = "test ok";
        ch[0] = 'g';
    }

    private static class A {
        int i = 0;
    }

    private static class Example {
        String str = new String("good");
        char[] ch = {'a', 'b', 'c'};
    }
}

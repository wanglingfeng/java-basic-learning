package com.reference.study;

import com.reference.study.bean.A;

import java.lang.ref.SoftReference;

/**
 * SoftReference：弱引用
 * 当弱引用某个对象设置为null时，这个对象此时只被弱引用依赖，除非JVM即将OutOfMemory，否则不会被GC回收
 * 所以这个例子的打印结果会一直持续下去
 *
 * Created by lfwang on 2017/2/21.
 */
public class SoftReferenceTest {

    public static void main(String... args) {
        A a = new A();
        a.str = "Hello, reference";
        SoftReference<A> soft = new SoftReference<>(a);

        a = null; // a设置为null后，a只会被弱引用依赖，除非JVM即将OutOfMemory，否则不会被GC回收
        int i = 0;

        while (null != soft.get()) {
            System.out.println(String.format("get str from object of WeakReference: %s. count: %d", soft.get().str, ++i));

            if (i % 10 == 0) {
                System.gc();
                System.out.println("System.gc() was invoked!");

                try {
                    Thread.sleep(1000); // 因为System.gc()不会立即执行GC，所以这里等待1秒后，继续执行
                } catch (InterruptedException ignored) {
                }
            }
        }

        System.out.println("object a was cleared by JVM!");
    }
}

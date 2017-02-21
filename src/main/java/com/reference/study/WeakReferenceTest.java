package com.reference.study;

import java.lang.ref.WeakReference;

/**
 * WeakReference：弱引用
 * 当弱引用某个对象设置为null时，这个对象此时只被弱引用依赖，那么GC会立刻回收这个对象
 *
 * Created by lfwang on 2017/2/21.
 */
public class WeakReferenceTest {

    public static void main(String... args) {
        A a = new A();
        a.str = "Hello, Reference";
        WeakReference<A> weak = new WeakReference<>(a);

        a = null; // a设置为null后，a只会被弱引用所依赖，执行gc则会立刻回收
        int i = 0;

        while (null != weak.get()) {
            System.out.println(String.format("get str from object of WeakReference: %s. count: %d", weak.get().str, ++i));

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

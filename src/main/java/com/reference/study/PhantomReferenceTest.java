package com.reference.study;

import com.reference.study.bean.A;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * 如果一个对象仅持有虚引用，在任何时候都可能被垃圾回收，通过虚引用的get方法永远获取到的数据为null，因此也被成为幽灵引用。
 * 虚引用与软引用和弱引用的一个区别在于：虚引用必须和引用队列联合使用
 * 虚引用主要用来跟踪对象被垃圾回收的活动。
 *
 * Created by lfwang on 2017/2/28.
 */
public class PhantomReferenceTest {

    public static ReferenceQueue<A> referenceQueue = new ReferenceQueue<>();

    public static void main(String... args) {
        for (int i = 0; i < 10; i++) {
            A a = new A();
            a.str = "Hello, Reference" + i;

            PhantomReference<A> reference = new PhantomReference<>(a, referenceQueue);
            System.out.println("Just created: " + reference.get()); // get()永远返回null
        }

        System.gc();
        checkQueue();
    }

    public static void checkQueue() {
        Reference<? extends A> reference = referenceQueue.poll();

        if (null != reference) {
            System.out.println("In queue: " + reference.get());
        }
    }
}

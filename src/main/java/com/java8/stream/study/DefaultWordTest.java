package com.java8.stream.study;

/**
 * default关键字测试
 *
 * 一个类中有多个相同的函数签名:
 * 1) 类中的方法优先级最高。类或父类中声明的方法的优先级高于任何声明为默认方法的优先级。
 * 2) 函数签名相同时，优先选择拥有最具体实现的默认方法的借口，即如果B继承了A，那么B就比A更加具体。
 * 3) 如果最后还是无法判断，继承了多个接口的类必须通过显式覆盖和调用期望的方法，显式地选择使用哪一个默认方法的实现。
 * Created by Lingfeng on 2016/7/28.
 */
public class DefaultWordTest {

    // 1)
    /*public interface A {
        default void hello() {
            System.out.println("Hello from A");
        }
    }

    public interface B extends A {
        default void hello() {
            System.out.println("Hello from B");
        }
    }

    public static class C implements B, A {
        public static void main(String... args) {
            new C().hello();
        }
    }*/

    // 2)
    /*public interface A {
        default void hello() {
            System.out.println("Hello from A");
        }
    }

    public interface B extends A {
        default void hello() {
            System.out.println("Hello from B");
        }
    }

    public static class D implements A {
        @Override
        public void hello() {
            System.out.println("Hello from D");
        }
    }

    public static class C extends D implements B, A {
        public static void main(String... args) {
            new C().hello();
        }
    }*/

    // 3)
    public interface A {
        default void hello() {
            System.out.println("Hello from A");
        }
    }

    public interface B {
        default void hello() {
            System.out.println("Hello from B");
        }
    }

    public static class C implements B, A {

        public static void main(String... args) {
            new C().hello();
        }

        @Override
        public void hello() {
            B.super.hello();
            System.out.println("Hello from C");
        }
    }
}

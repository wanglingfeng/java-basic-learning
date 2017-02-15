package com.init.study;

/**
 * Created by lfwang on 2017/2/15.
 */
public class InitTest {

    public static void main(String... args) {
        Son son = new Son();
    }
}

class Father {

    static {
        System.out.println("Father static code block");
    }

    {
        System.out.println("Father code block");
    }

    public Father() {
        System.out.println("Father construct method");
    }
}

class Son extends Father {

    static {
        System.out.println("Son static code block");
    }

    {
        System.out.println("Son code block");
    }

    public Son() {
        System.out.println("Son construct method");
    }
}

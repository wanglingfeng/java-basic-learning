package com.annotation.study;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lingfeng on 2016/6/3.
 */
@TestA(name = "type", gid = Long.class) // 使用了类注解
public class UserAnnotation {

    @TestA(name = "param", id = 1, gid = Long.class) // 使用了类成员注解
    private Integer age;

    @TestA(name = "construct", id = 2, gid = Long.class) // 使用了构造方法注解
    public UserAnnotation() {

    }

    @TestA(name = "public method", id = 3, gid = Long.class) // 使用了类方法注解
    public void a() {

        /*@TestA // 使用了局部变量注解*/
        Map m = new HashMap<>(0);
    }

    @TestA(name = "protected method", id = 4, gid = Long.class)
    protected void b() {
        Map m = new HashMap<>(0);
    }

    @TestA(name = "private method", id = 5, gid = Long.class)
    private void c() {
        Map m = new HashMap<>(0);
    }


    /*public void b(@TestA Integer a) { // 使用了方法参数注解

    }*/
}

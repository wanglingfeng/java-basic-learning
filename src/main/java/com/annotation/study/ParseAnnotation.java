package com.annotation.study;

import org.aspectj.weaver.ast.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by Lingfeng on 2016/6/3.
 */
public class ParseAnnotation {

    public static void main(String[] args) throws ClassNotFoundException {
        parseTypeAnnotation();
        parseMethodAnnotation();
        parseConstructAnnotation();
    }

    public static void parseTypeAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("com.annotation.study.UserAnnotation");

        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations) {
            TestA testA = (TestA) annotation;

            printTestA(testA);
        }
    }

    public static void parseMethodAnnotation() {
        Method[] methods = UserAnnotation.class.getDeclaredMethods();

        for (Method method : methods) {
            boolean hasAnnotation = method.isAnnotationPresent(TestA.class);

            if (hasAnnotation) {
                TestA annotation = method.getAnnotation(TestA.class);

                printTestA(annotation);
            }
        }
    }

    private static void parseConstructAnnotation() {
        Constructor[] constructors = UserAnnotation.class.getConstructors();

        for (Constructor constructor : constructors) {
            boolean hasAnnotation = constructor.isAnnotationPresent(TestA.class);

            if (hasAnnotation) {
                TestA annotation = (TestA) constructor.getAnnotation(TestA.class);

                printTestA(annotation);
            }
        }
    }

    private static void printTestA(TestA testA) {
        System.out.println("id= " + testA.id() + "; name= " + testA.name() + "; gid= " + testA.gid());
    }
}

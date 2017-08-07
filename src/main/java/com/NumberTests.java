package com;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * Add Some Entropy to Your JVM
 * 
 * Created by lfwang on 2017/8/4.
 */
public class NumberTests {
    
    public static void main(String... args) throws 
            ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        // Extract the IntegerCache through reflection
        Class<?> clazz = Class.forName("java.lang.Integer$IntegerCache");

        Field field = clazz.getDeclaredField("cache");
        field.setAccessible(true);
        Integer[] cache = (Integer[]) field.get(clazz);
        
        // Rewrite the Integer cache
        for (int i = 0; i < cache.length; i++) {
            cache[i] = new Random().nextInt(cache.length);
        }
        
        // Prove randomness
        for (int i = 0; i < 10; i++) {
            System.out.println((Integer) i);
        }
    }
}

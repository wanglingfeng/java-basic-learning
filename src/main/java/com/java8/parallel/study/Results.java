package com.java8.parallel.study;

/**
 * Created by Lingfeng on 2016/8/1.
 */
public interface Results {

    <R> R get(Object key);
}

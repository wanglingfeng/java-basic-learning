package com.observer.study;

/**
 * Created by Lingfeng on 2016/4/22.
 */
public class SimpleTest {

    public static void main(String[] args) {
        SimpleObservable doc = new SimpleObservable();
        new SimpleObserver(doc);

        doc.setData(1);
        doc.setData(2);
        doc.setData(2);
        doc.setData(3);
    }
}

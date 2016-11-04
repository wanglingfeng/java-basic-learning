package com.observer.study;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者类
 *
 * Created by Lingfeng on 2016/4/22.
 */
public class SimpleObserver implements Observer {

    public SimpleObserver(SimpleObservable simpleObservable) {
        simpleObservable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Data has changed to " + ((SimpleObservable) o).getData());
    }
}

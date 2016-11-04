package com.observer.study;

import java.util.Observable;

/**
 * 被观察者类
 *
 * Created by Lingfeng on 2016/4/22.
 */
public class SimpleObservable extends Observable {

    private int data = 0;

    public int getData() {
        return this.data;
    }

    public void setData(int i) {
        if (this.data != i) {
            this.data = i;
            setChanged();
            // 只有在setChanged()被调用后，notifyObservers()才会去调用update()，否则什么都不干。
            notifyObservers();
        }
    }
}

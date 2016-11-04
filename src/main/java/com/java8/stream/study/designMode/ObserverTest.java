package com.java8.stream.study.designMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * Created by Lingfeng on 2016/7/28.
 */
public class ObserverTest {

    public static void main(String... args) {
        Feed f = new Feed();

        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());

        f.notifyObservers("THe queen said her favourite book is Java 8 in Action!");

        // Lambda表达式实现
        f.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("lambda")) {
                System.out.println("Lambda receive message... " + tweet);
            }
        });
    }

    interface Observer {
        void notify(String tweet);
    }

    static class NYTimes implements Observer {

        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        }
    }

    static class Guardian implements Observer {

        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet another news in London...  " + tweet);
            }
        }
    }

    static class LeMonde implements Observer {

        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("wine")) {
                System.out.println("Today cheese, win and news! " + tweet);
            }
        }
    }

    interface Subject {
        void registerObserver(Observer o);
        void notifyObservers(String tweet);
    }

    static class Feed implements Subject {

        private final List<Observer> observers = new ArrayList<>();

        @Override
        public void registerObserver(Observer o) {
            this.observers.add(o);
        }

        @Override
        public void notifyObservers(String tweet) {
            observers.forEach(o -> o.notify(tweet));
        }
    }
}

package com.rxjava.study;

import rx.Observable;

/**
 * Created by Lingfeng on 2016/5/23.
 */
public class RxjavaTest {

    public static void main(String[] args) {
        RxjavaTest test = new RxjavaTest();
        test.helloWorld();
    }

    public void helloWorld() {
        // 方法1
        /*Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("Hello world");
                        subscriber.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        myObservable.subscribe(mySubscriber);*/

        // 方法2
        /*Observable<String> myObservable = Observable.just("hello world", "hi");

        Action1<String> onNextAction = System.out::println;

        myObservable.subscribe(onNextAction);*/

        // 方法3
        /*Observable.just("hello world", "oops").subscribe(System.out::println);*/

        // 方法4
        /*Observable.just("hello ", "oops ").subscribe(s -> System.out.println(s + "joseph"));*/

        Observable.just("hello ", "oops ").map(s -> s + "joseph").map(s -> s + " wlf").subscribe(System.out::println);

        Observable.just("hello, world!").map(String::hashCode).map(i -> Integer.toString(i)).subscribe(System.out::println);
    }
}

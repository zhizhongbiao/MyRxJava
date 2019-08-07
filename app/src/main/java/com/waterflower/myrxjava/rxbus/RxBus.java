package com.waterflower.myrxjava.rxbus;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * FileName :  RxBus
 * Author   :  zhizhongbiao
 * Date     :  2019/8/7
 * Describe :
 */

public class RxBus {

    private final PublishSubject<Object> bus;

    private RxBus() {
        bus = PublishSubject.create();// subscriber receive all subsequent items that are emitted after subscribers subscribe
//        bus = ReplaySubject.create();// subscriber receive all subsequent items ， no matter when subscribers subscribe
//        bus = BehaviorSubject.create();// subscriber receive all subsequent items that are emitted after subscribers subscribe and the latest item before subscribers subscribe
//        bus = AsyncSubject.create();// subscriber receive only the latest item before complete
    }

    public static RxBus getInstance() {
        return BusHolder.bus;
    }


    public void post(Object event) {
        bus.onNext(event);
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }

    public <T> Observable<T> toObservable(Class<T> clz) {

        return bus.ofType(clz);
    }


    private static class BusHolder {
        private final static RxBus bus = new RxBus();
    }
}

package com.waterflower.myrxjava.publisher;


import com.waterflower.myrxjava.functions.MapOnSubscribe;
import com.waterflower.myrxjava.functions.MapTransformer;
import com.waterflower.myrxjava.subscriber.MySubscriber;
import com.waterflower.myrxjava.thread.Scheduler;
import com.waterflower.myrxjava.utils.LogUtils;

/**
 * FileName :  MyObservable
 * Author   :  zhizhongbiao
 * Date     :  2019/8/5
 * Describe :
 */

public class MyObservable<T> {

    private final MyOnSubscribe onSubscriber;

    private MyObservable(MyOnSubscribe<T> onSubscriber) {
        this.onSubscriber = onSubscriber;
    }


    public static <T> MyObservable<T> create(MyOnSubscribe<T> onSubscriber) {
        return new MyObservable(onSubscriber);
    }


    public void subscribe(MySubscriber subscriber) {
        subscriber.onStart();
        onSubscriber.call(subscriber);
    }

    public interface MyOnSubscribe<T> {
        void call(MySubscriber<? super T> subscriber);
    }


    // New way
    public <R> MyObservable<R> mapOperate(final MapTransformer<? super T, ? extends R> mapTransformer) {
        return create(new MapOnSubscribe<T, R>(MyObservable.this, mapTransformer));
    }


//     Old way
//    public <R> MyObservable<R> mapOperate(final MapTransformer<? super T, ? extends R> mapTransformer) {
//        return create(new MyOnSubscribe<R>() {
//            @Override
//            public void call(final MySubscriber<? super R> subscriber) {
//                MyObservable.this.subscribe(new MySubscriber<T>() {
//                    @Override
//                    public void onComplete() {
//                        subscriber.onComplete();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        subscriber.onError(e);
//                    }
//
//                    @Override
//                    public void onNext(T t) {
//                       subscriber.onNext(mapTransformer.transform(t));
//                    }
//                });
//            }
//        });
//    }


    public MyObservable<T> subscribeOn(final Scheduler scheduler) {
        return create(new MyOnSubscribe<T>() {
            @Override
            public void call(final MySubscriber<? super T> subscriber) {
                subscriber.onStart();
                scheduler.createWorker().schedule(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.i("Run in the io thread : " + Thread.currentThread().getName());
                        MyObservable.this.onSubscriber.call(subscriber);
                    }
                });
            }
        });
    }


    public MyObservable<T> observeOn(final Scheduler scheduler) {
        return create(new MyOnSubscribe<T>() {
            @Override
            public void call(final MySubscriber<? super T> subscriber) {
                subscriber.onStart();
                final Scheduler.Worker worker = scheduler.createWorker();
                MyObservable.this.onSubscriber.call(new MySubscriber<T>() {
                    @Override
                    public void onComplete() {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onComplete();
                            }
                        });
                    }

                    @Override
                    public void onError(final Throwable e) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onError(e);
                            }
                        });
                    }

                    @Override
                    public void onNext(final T t) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onNext(t);
                            }
                        });
                    }
                });
            }
        });
    }


}

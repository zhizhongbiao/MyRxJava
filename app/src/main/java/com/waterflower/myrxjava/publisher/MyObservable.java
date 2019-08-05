package com.waterflower.myrxjava.publisher;


import com.waterflower.myrxjava.functions.MapTransformer;
import com.waterflower.myrxjava.subscriber.MySubscriber;

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


    public void onSubscribe(MySubscriber subscriber) {
        subscriber.onStart();
        onSubscriber.onCall(subscriber);
    }

    public interface MyOnSubscribe<T> {
        void onCall(MySubscriber<? super T> subscriber);
    }


    public <R> MyObservable<R> mapOperate(final MapTransformer<? super T, ? extends R> mapTransformer) {
        return create(new MyOnSubscribe<R>() {
            @Override
            public void onCall(final MySubscriber<? super R> subscriber) {
                MyObservable.this.onSubscribe(new MySubscriber<T>() {
                    @Override
                    public void onComplete() {
                        subscriber.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(T t) {
                       subscriber.onNext(mapTransformer.transform(t));
                    }
                });
            }
        });
    }
}

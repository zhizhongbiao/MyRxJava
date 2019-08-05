package com.waterflower.myrxjava.functions;


import com.waterflower.myrxjava.subscriber.MySubscriber;

/**
 * FileName :  MapSubscriber
 * Author   :  zhizhongbiao
 * Date     :  2019/8/5
 * Describe :
 */

public class MapSubscriber<T, R> extends MySubscriber<R> {


    final MySubscriber<? super T> actualSubscriber;
    final MapTransformer<R, T> transformer;

    public MapSubscriber(MySubscriber<? super T> actualSubscriber, MapTransformer<R, T> transformer) {
        this.actualSubscriber = actualSubscriber;
        this.transformer = transformer;
    }

    @Override
    public void onComplete() {
        actualSubscriber.onComplete();
    }

    @Override
    public void onError(Throwable e) {
        actualSubscriber.onError(e);
    }

    @Override
    public void onNext(R r) {
        actualSubscriber.onNext(transformer.transform(r));
    }
}

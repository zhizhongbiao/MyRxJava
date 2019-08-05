package com.waterflower.myrxjava.functions;

import com.waterflower.myrxjava.publisher.MyObservable;
import com.waterflower.myrxjava.subscriber.MySubscriber;

/**
 * FileName :  MapOnSubscribe
 * Author   :  zhizhongbiao
 * Date     :  2019/8/5
 * Describe :
 */

public class MapOnSubscribe<T,R>  implements MyObservable.MyOnSubscribe<R>{

    private final MyObservable<T> sourceObservable;
    private final MapTransformer<? super T,? extends R> transformer;

    public MapOnSubscribe(MyObservable<T> sourceObservable, MapTransformer<? super T,? extends R> transformer) {
        this.sourceObservable = sourceObservable;
        this.transformer = transformer;
    }

    @Override
    public void call(MySubscriber<? super R> subscriber) {
        sourceObservable.subscribe(new MapSubscriber<R,T>(subscriber,transformer));
    }


}

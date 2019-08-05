package com.waterflower.myrxjava.functions;

import com.waterflower.myrxjava.R;
import com.waterflower.myrxjava.publisher.MyObservable;
import com.waterflower.myrxjava.subscriber.MySubscriber;

/**
 * FileName :  MapOnSubscribe
 * Author   :  zhizhongbiao
 * Date     :  2019/8/5
 * Describe :
 */

public class MapOnSubscribe<T>  implements MyObservable.MyOnSubscribe<T>{

    private final MapTransformer transformer;
    private final MyObservable sourceObservable;

    public MapOnSubscribe(MapTransformer<T,R> transformer, MyObservable sourceObservable) {
        this.transformer = transformer;
        this.sourceObservable = sourceObservable;
    }

    @Override
    public void onCall(MySubscriber<? super T> subscriber) {
        sourceObservable.onSubscribe(new MapSubscriber(subscriber,transformer));
    }


}

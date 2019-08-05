package com.waterflower.myrxjava.Demo;

import com.waterflower.myrxjava.functions.MapTransformer;
import com.waterflower.myrxjava.publisher.MyObservable;
import com.waterflower.myrxjava.subscriber.MySubscriber;

/**
 * FileName :  LoadDataPresenter
 * Author   :  zhizhongbiao
 * Date     :  2019/8/5
 * Describe :
 */

public class LoadDataPresenter {


    public void loadData(final String url) {
        
        MyObservable.create(new MyObservable.MyOnSubscribe<String>() {
            @Override
            public void onCall(MySubscriber<? super String> subscriber) {
                subscriber.onNext(getDataFrom(url));
            }
        }).mapOperate(new MapTransformer<String, Integer>() {
            @Override
            public Integer transform(String source) {
                return source == null ? -1 : 999;
            }
        }).onSubscribe(new MySubscriber<Integer>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                showDataToView(integer+"data");
            }
        });

    }

    private void showDataToView(String data) {

    }

    private String getDataFrom(String url) {
        return null;
    }
}

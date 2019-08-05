package com.waterflower.myrxjava.Demo;

import com.waterflower.myrxjava.functions.MapTransformer;
import com.waterflower.myrxjava.publisher.MyObservable;
import com.waterflower.myrxjava.subscriber.MySubscriber;
import com.waterflower.myrxjava.thread.ScheduleFactory;

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
            public void call(MySubscriber<? super String> subscriber) {
                subscriber.onNext(getDataFrom(url));
            }
        }).mapOperate(new MapTransformer<String, Integer>() {
            @Override
            public Integer transform(String source) {
                return source == null ? -1 : 999;
            }
        }).subscribe(new MySubscriber<Integer>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                showDataToView(integer + "data");
            }
        });


        MyObservable.create(new MyObservable.MyOnSubscribe<String>() {
            @Override
            public void call(MySubscriber<? super String> subscriber) {

            }
        })
                .observeOn(ScheduleFactory.io())
                .observeOn(ScheduleFactory.io())

                .subscribeOn(ScheduleFactory.io())

                .mapOperate(new MapTransformer<String, String>() {
                    @Override
                    public String transform(String source) {
                        return source;
                    }
                })
                .subscribeOn(ScheduleFactory.io())
                .subscribe(new MySubscriber() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });

    }

    private void showDataToView(String data) {

    }

    private String getDataFrom(String url) {
        return null;
    }
}

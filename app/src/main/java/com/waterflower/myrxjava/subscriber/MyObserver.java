package com.waterflower.myrxjava.subscriber;

/**
 * FileName :  MyObserver
 * Author   :  zhizhongbiao
 * Date     :  2019/8/5
 * Describe :
 */

public interface MyObserver<T> {

    void onComplete();
    void onError(Throwable e);
    void onNext(T t);
}

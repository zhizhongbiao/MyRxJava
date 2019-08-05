package com.waterflower.myrxjava.functions;

/**
 * FileName :  MapTransformer
 * Author   :  zhizhongbiao
 * Date     :  2019/8/5
 * Describe :
 */

public interface MapTransformer<T,R> {
    R transform(T source);
}

package com.waterflower.myrxjava.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * FileName :  ScheduleFactory
 * Author   :  zhizhongbiao
 * Date     :  2019/8/5
 * Describe :
 */

public class ScheduleFactory {

    private static final Scheduler ioScheduler=new Scheduler(Executors.newSingleThreadExecutor());

    public static Scheduler io()
    {
        return ioScheduler;
    }
}

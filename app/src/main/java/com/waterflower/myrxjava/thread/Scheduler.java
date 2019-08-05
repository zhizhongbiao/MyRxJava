package com.waterflower.myrxjava.thread;

import java.util.concurrent.Executor;

/**
 * FileName :  Scheduler
 * Author   :  zhizhongbiao
 * Date     :  2019/8/5
 * Describe :
 */

public class Scheduler {
    private final Executor executor;


    public Scheduler(Executor executor) {
        this.executor = executor;
    }


    public Worker createWorker() {
        return new Worker(executor);
    }


    public static class Worker {
        private final Executor executor;

        public Worker(Executor executor) {
            this.executor = executor;
        }

        public void schedule(Runnable task) {
            executor.execute(task);
        }
    }
}

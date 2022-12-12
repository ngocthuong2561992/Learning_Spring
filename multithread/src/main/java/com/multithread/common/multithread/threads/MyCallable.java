package com.multithread.common.multithread.threads;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    private long waitTime;
    public MyCallable(int timeInMillis) {
        this.waitTime=timeInMillis;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(waitTime);
        return Thread.currentThread().getName();
    }
}

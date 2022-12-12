package com.multithread.common.multithread.threads;

public class HashMapProcessor implements Runnable {
    private String[] arr;

    public HashMapProcessor(String[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        processArr(Thread.currentThread().getName());
    }

    private void processArr(String name) {
        for(int i = 0; i < arr.length; i++){
            //process data and append thread name
            processSomething(i);
            addThreadName(i, name);
        }
    }

//    private final Object lock = new Object();
    private void addThreadName(int i, String name) {
        synchronized(arr) {
            arr[i] = arr[i] + ":" + name;
        }
    }

//    without synchronized thread not safe
//    private void addThreadName(int i, String name) {
//        strArr[i] = strArr[i] +":"+name;
//    }

    private void processSomething(int index) {
        try {
            Thread.sleep(index * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

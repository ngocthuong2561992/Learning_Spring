package com.multithread.common.multithread;

public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;
    private static Object mutex = new Object();

    private ThreadSafeSingleton() {}

    public static ThreadSafeSingleton getInstance() {
        ThreadSafeSingleton result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    instance = result = new ThreadSafeSingleton();
                }
            }
        }
        return result;
    }
}

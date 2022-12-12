package com.multithread.common.multithread;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadLockDemo {
    private final ReentrantLock lock = new ReentrantLock();
    private Integer count = 0;

    //Locking using Lock and ReentrantLock
    public Integer getCount() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " gets Count: " + count);
            return count++;
        } finally {
            lock.unlock();
        }
    }

    //Implicit locking using synchronized keyword
    public synchronized int getCountTwo() {
        return count++;
    }

    public static void main(String[] args) {
        final ThreadLockDemo counter = new ThreadLockDemo();
        Thread t1 = new Thread(() -> {
            while (counter.getCount() < 6) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (counter.getCount() < 6) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}

package com.multithread.common.multithread.threads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class Worker implements Runnable {
    String name;
    ReentrantLock lock;
    public Worker(ReentrantLock lock, String name) {
        this.lock = lock;
        this.name = name;
    }
    public void run() {
        boolean done = false;
        while (!done) {
            if(lock.tryLock()) {
                try {
                    System.out.println("task name - "+ name + " outer lock acquired at "
                            + getCurrentDateFormat() + " Doing outer work");
                    Thread.sleep(1500);

                    lock.lock();
                    try {
                        System.out.println("task name - "+ name + " inner lock acquired at "
                                + getCurrentDateFormat() + " Doing inner work");
                        System.out.println("Lock Hold Count - "+ lock.getHoldCount());
                        Thread.sleep(1500);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        System.out.println("task name - " + name + " releasing inner lock");
                        lock.unlock();
                    }
                    System.out.println("Lock Hold Count - " + lock.getHoldCount());
                    System.out.println("task name - " + name + " work done");
                    done = true;
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("task name - " + name + " releasing outer lock");
                    lock.unlock();
                    System.out.println("Lock Hold Count - " + lock.getHoldCount());
                }
            }else {
                System.out.println("task name - " + name + " waiting for lock");
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getCurrentDateFormat() {
        return new SimpleDateFormat("hh:mm:ss").format(new Date());
    }
}

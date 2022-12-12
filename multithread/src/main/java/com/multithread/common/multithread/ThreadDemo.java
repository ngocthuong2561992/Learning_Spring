package com.multithread.common.multithread;

import com.multithread.common.multithread.threads.*;

import java.util.Arrays;

public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        createThread();

//      start second thread after waiting join() or first thread dead
//        threadJoin();

//      thread waiting other thread notify
//        waitNotify();

//        threadSafe();
    }

    public static void createThread() {
        Thread t1 = new Thread(new HeavyWorkRunnable(), "t1");
        Thread t2 = new MyThread("t2");
        Runnable t3 = () -> System.out.println("Start t3");
        t1.start();
        t2.start();
        t3.run();
    }

    public static void threadJoin() {
        Thread t1 = new Thread(new MyRunnable(), "t1");
        Thread t2 = new Thread(new MyRunnable(), "t2");
        t1.start();
        //start second thread after waiting for 2 seconds or if it's dead
        try {
            t1.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
    public static void waitNotify() {
//        waiter and waiter1 will wait until msg notify

        Message msg = new Message("process it");
        WaiterRunnable waiter = new WaiterRunnable(msg);
        new Thread(waiter,"waiter").start();

        WaiterRunnable waiter1 = new WaiterRunnable(msg);
        new Thread(waiter1, "waiter1").start();

        NotifierRunnable notifier = new NotifierRunnable(msg);
        new Thread(notifier, "notifier").start();
        System.out.println("All the threads are started");
    }

    public static void threadSafe() throws InterruptedException {
        String[] arr = {"1","2","3"};
        HashMapProcessor hmp = new HashMapProcessor(arr);
        Thread t1 = new Thread(hmp, "t1");
        Thread t2 = new Thread(hmp, "t2");
        Thread t3 = new Thread(hmp, "t3");
        long start = System.currentTimeMillis();
        t1.start();t2.start();t3.start();
        t1.join();t2.join();t3.join();

//        t1.start(); t1.join();
//        t2.start(); t2.join();
//        t3.start(); t3.join();

        System.out.println("Time taken= "+(System.currentTimeMillis()-start));
        System.out.println(Arrays.asList(arr));
    }
}

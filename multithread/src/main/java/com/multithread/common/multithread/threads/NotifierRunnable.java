package com.multithread.common.multithread.threads;

public class NotifierRunnable implements Runnable {

    private Message msg;

    public NotifierRunnable(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name+" thread started");
        try {
            Thread.sleep(1000);
            synchronized (msg) {
                msg.setMsg(name+" Notifier work done");

//              notify() method has wake up only one of them,
//              the other thread is still waiting to get notified
//              notifyAll() method has wake up all threads
//                msg.notify();
                 msg.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

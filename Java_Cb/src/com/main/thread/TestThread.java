package com.main.thread;

import java.util.concurrent.ThreadLocalRandom;

public class TestThread extends Thread {
    private String threadName;

    public TestThread(String threadName) {
        super(threadName);
        this.threadName = threadName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getThreadName() +  " " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TestThread t1 =  new TestThread("a");
        TestThread t2 =  new TestThread("b");
        TestThread t3 =  new TestThread("c");
        t1.start();
        try {
            t1.join(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        t3.start();


        System.out.println("Xet do uu tien nhung ko chac chan");

        t1.setPriority(1);
        t2.setPriority(10);
        t3.setPriority(5);

//        t1.start();
//        t2.start();
//        t3.start();

        System.out.println("Deamon thread chay ngam nhu don rac");
        t1.setDaemon(true);
        System.out.println(t1.isDaemon());
    }
}

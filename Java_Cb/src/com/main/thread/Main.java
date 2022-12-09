package com.main.thread;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
//        MyThread1 my1= new MyThread1();
//        Thread t = new Thread(my1);
//
//        MyThread2 my2= new MyThread2();
//        t.start();
//        my2.start();

        Account account = new Account();

        Thread t1 = new Thread(account);
        Thread t2 = new Thread(account);
        Thread t3 = new Thread(account);

        t1.start();
        t2.start();
        t3.start();

    }
}

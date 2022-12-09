package com.main.thread;


public class Account implements Runnable{
    private int balance = 1000;
    @Override
    public void run() {
        balance = balance -1000;
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (balance < 1000) {
            System.out.println("Het Tien");
        } else {
            System.out.println("Con Tien");
        }
    }
}

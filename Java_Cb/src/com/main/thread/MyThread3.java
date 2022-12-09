package com.main.thread;

public class MyThread3 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(super.getName() + ":" + i);
        }
    }

    public static void main(String[] args) {
        MyThread3 t1 = new MyThread3();
        MyThread3 t2 = new MyThread3();
        //set ten thuoc tinh
        t1.setName("T1");
        t2.setName("T2");
        //chay thread
        t1.start();
        t2.start();
    }
}

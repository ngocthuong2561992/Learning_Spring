package com.main.thread;

public class TestThread2 implements Runnable{

    private int tong;

    public TestThread2(){
        this.tong = 1000;
    }

    public synchronized void rutTien() throws InterruptedException {
        if (tong > 0) {
            Thread.sleep(1000);
            tong = tong - 1000;
            System.out.println("tong :"  +tong);
        } else {
            System.out.println("khong con tien");
        }
    }

    @Override
    public void run() {
        try {
            rutTien();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestThread2 t = new TestThread2();

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
    }
}

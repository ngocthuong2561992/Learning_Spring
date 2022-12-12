package com.multithread.common.multithread;

public class ThreadDeadlock {

    public static void main(String[] args) throws InterruptedException {
        Integer num1 = 1;
        Integer num2 = 2;
        Integer num3 = 3;

        Thread t1 = new Thread(new SyncThread(num1, num2), "t1");
        Thread t2 = new Thread(new SyncThread(num2, num3), "t2");
        Thread t3 = new Thread(new SyncThread(num3, num1), "t3");

        t1.start();
        Thread.sleep(1000);
        t2.start();
        Thread.sleep(1000);
        t3.start();
    }
    static class SyncThread implements Runnable{
        private final Integer num1;
        private final Integer num2;

        public SyncThread(Integer o1, Integer o2){
            this.num1=o1;
            this.num2=o2;
        }
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " before synchronized "+num1);
            synchronized (num1) {
                System.out.println(name + " synchronized "+num1);
                work();
                System.out.println(name + " before synchronized "+num2);
                synchronized (num2) {
                    System.out.println(name + " synchronized "+num2);
                    work();
                }
                System.out.println(name + " after "+num2);
            }
            System.out.println(name + " after "+num1);
            System.out.println(name + " finished execution.");
        }
        private void work() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


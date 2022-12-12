package com.multithread.common.multithread;

import com.multithread.common.multithread.threads.ThreadLocalExample;

import java.util.Random;

public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample threadLocal = new ThreadLocalExample();
        for(int i = 0; i < 3; i++) {
            Thread thread = new Thread(threadLocal, "" + i);
            Thread.sleep(new Random().nextInt(1000));
            thread.start();
        }
    }

}

package com.multithread.common.multithread;

import com.multithread.common.multithread.threads.MyCallable;
import com.multithread.common.multithread.threads.MyMonitorThread;
import com.multithread.common.multithread.threads.Worker;
import com.multithread.common.multithread.threads.WorkerThread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolDemo {
    static final int MAX_THREAD = 2;
    public static void main(String[] args) throws InterruptedException {
//        executorServiceExample();
        threadPoolExecutorExample();
//        executorServiceWithCalableFuture();
//        futureTaskExample();
    }
    static void executorServiceExample() {
        ReentrantLock lock = new ReentrantLock();
        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREAD);
        Runnable w1 = new Worker(lock, "Job1");
        Runnable w2 = new Worker(lock, "Job2");
        Runnable w3 = new Worker(lock, "Job3");
        Runnable w4 = new Worker(lock, "Job4");
        pool.execute(w1);
        pool.execute(w2);
        pool.execute(w3);
        pool.execute(w4);
        pool.shutdown();
    }
    static void executorServiceWithCalableFuture() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<String>> list = new ArrayList<>();
        Callable callable = new MyCallable(1000);
        for(int i=0; i< 100; i++){
            Future future = executor.submit(callable);
            list.add(future);
        }
        for(Future<String> fut : list){
            try {
                System.out.println(new Date()+ "::"+fut.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
    static void futureTaskExample() {
        FutureTask<String> futureTask1 = new FutureTask<>(new MyCallable(1000));
        FutureTask<String> futureTask2 = new FutureTask<>(new MyCallable(2000));

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(futureTask1);
        executor.execute(futureTask2);
        while (true) {
            try {
                if(futureTask1.isDone() && futureTask2.isDone()){
                    System.out.println("Done");
                    executor.shutdown();
                    return;
                }
                if(!futureTask1.isDone()){
                    System.out.println("FutureTask1 output="+futureTask1.get());
                }

                System.out.println("Waiting for FutureTask2 to complete");
                String s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
                if(s !=null){
                    System.out.println("FutureTask2 output="+s);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }catch(TimeoutException e){
                //do nothing
            }
        }
    }
    static void threadPoolExecutorExample() throws InterruptedException {
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(
                2, 4,
                10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                (r, executor) -> System.out.println(r.toString() + " is rejected"));
        MyMonitorThread monitor = new MyMonitorThread(executorPool, 3);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
        for(int i=0; i<10; i++) {
            executorPool.execute(new WorkerThread("cmd"+i));
        }

        Thread.sleep(30000);
        executorPool.shutdown();
        Thread.sleep(5000);
        monitor.shutdown();
    }

}


package com.multithread.common.multithread;

import com.multithread.common.multithread.threads.CompletableFutureHandler;

public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
//        System.out.println("ConcurrentExecution: " + CompletableFutureHandler.calculateAsync().get());
//        System.out.println("ApplyComposeCombine: " + CompletableFutureHandler.futureCompose().get());
        System.out.println("multipleFutures: " + CompletableFutureHandler.multipleFuturesExample());
        System.out.println("handlingErrors: " + CompletableFutureHandler.handlingErrors().get());

    }

}

package com.multithread.common.multithread.threads;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureHandler {
    public static List<String> multipleFuturesExample() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

//        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
//        return combinedFuture;

        return Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
    public static CompletableFuture<String> handlingErrors() {
        String name = null;
//        String name = "world";

        CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("name null!");
            }
            return "Hello, " + name;
        }).completeExceptionally(new RuntimeException("Calculation failed!"));

        return CompletableFuture
            .supplyAsync(() -> {
                if (name == null) {
                    throw new RuntimeException("Computation error!");
                }
                return "Hello, " + name;
            })
            .handle((res, ex) -> {
                if(res != null) {
                    return res;
                }
                return ex.getMessage();
            });

    }
    public static Future<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            TimeUnit.SECONDS.sleep(3);
            completableFuture.complete("Hello");
            return null;
//            return Thread.currentThread().getName();
        });
        return completableFuture;
    }
    public static CompletableFuture<String> futureCompose() {
        return CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenApply(s -> s + " new")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " beautiful"))
                .thenCombine(
                    CompletableFuture.supplyAsync(() -> " World"),
                    (s1, s2) -> s1 + s2
                );
    }
}

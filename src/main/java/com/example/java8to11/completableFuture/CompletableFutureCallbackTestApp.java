package com.example.java8to11.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureCallbackTestApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenApply();
        thenAccept();
        thenRun();
        customThreadPool();
    }

    private static void thenApply() throws ExecutionException, InterruptedException {
        System.out.println("CompletableFutureTestApp.callbackThenApply");
        /**
         * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
         * - 리턴값을 받아서 다른 값으로 바꾸고 리턴하는 콜백
         */
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply(s -> s.toUpperCase());

        System.out.println(future.get());
    }

    private static void thenAccept() throws ExecutionException, InterruptedException {
        System.out.println("CompletableFutureTestApp.thenAccept");
        /**
         * public CompletableFuture<Void> thenAccept(Consumer<? super T> action)
         * - 리턴값으로 또 다른 작업을 처리하고 리턴이 없는 콜백
         */
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept(s -> {
            System.out.println(s.toUpperCase());
        });

        future.get();
    }

    private static void thenRun() throws ExecutionException, InterruptedException {
        System.out.println("CompletableFutureTestApp.thenRun");
        /**
         * public CompletableFuture<Void> thenRun(Runnable action)
         * - 리턴값을 받지 않고 다른 작업을 처리하는 콜백
         */
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        future.get();
    }

    private static void customThreadPool() throws ExecutionException, InterruptedException {
        System.out.println("\nCompletableFutureTestApp.customThreadPool");
        /**
         * 원하는 Executor(thread-pool)를 사용해서 실행 가능
         * - default: ForkJoinPool.commonPool()
         */
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        }, executorService).thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        }, executorService);

        future.get();

        executorService.shutdown();
    }
}

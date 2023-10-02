package com.example.java8to11.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTestApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        complete();
        runAsync();
        supplyAsync();
    }

    private static void complete() throws ExecutionException, InterruptedException {
        System.out.println("CompletableFutureTestApp.complete");
        /**
         * CompletableFuture
         * - 외부에서 Complete 울 명시적으로 시킬 수 있음
         * - Executor 를 만들어서 사용할 필요가 없음
         */
        CompletableFuture<String> future = new CompletableFuture<>();
        // 특정 시간 이내에 응답이 없으면 기본 값으로 리턴하도록 설정
        future.complete("aaron");

        System.out.println(future.get());
    }

    private static void runAsync() throws ExecutionException, InterruptedException {
        System.out.println("\nCompletableFutureTestApp.runAsync");
        /**
         * runAsync(): 리턴값이 없는 비동기 작업
         */
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("Hello " + Thread.currentThread().getName()));

        /**
         * public T get() throws InterruptedException, ExecutionException: 결과 반환
         * public T join(): get() 과 동일하지만 Unchecked Exception
         */
        future.get();
    }

    private static void supplyAsync() throws ExecutionException, InterruptedException {
        System.out.println("\nCompletableFutureTestApp.runAsync");
        /**
         * supplyAsync(): 리턴값이 있는 비동기 작업
         */
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        System.out.println(future.get());
    }
}

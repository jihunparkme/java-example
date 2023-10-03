package com.example.java8to11.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExceptionTestApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        exeptionally();
        handle();
    }

    private static void exeptionally() throws InterruptedException, ExecutionException {
        System.out.println("CompletableFutureExceptionTestApp.exeptionally");
        /**
         * public CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn)
         */
        boolean throwError = true;
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error!";
        });

        System.out.println(hello.get());
    }

    private static void handle() throws InterruptedException, ExecutionException {
        System.out.println("\nCompletableFutureExceptionTestApp.handle");
        /**
         * public <U> CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn)
         * - 성공 케이스와 예외 케이스 모두 핸들링
         */
        boolean throwError = true;
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println(ex);
                return "Error!";
            }
            return result;
        });

        System.out.println(hello.get());
    }
}

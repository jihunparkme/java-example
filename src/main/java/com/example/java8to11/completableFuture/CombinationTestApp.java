package com.example.java8to11.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CombinationTestApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenCompose();
        thenCombine();
        allOf();
        anyOf();
    }

    private static void thenCompose() throws InterruptedException, ExecutionException {
        System.out.println("CombinationTestApp.thenCompose");
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        /**
         * public <U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn)
         * - 연관이 있는 두 작업이 서로 이어서 실행하도록 조합
         */
        CompletableFuture<String> future = hello.thenCompose(CombinationTestApp::getWorld);
        System.out.println(future.get());
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    }

    private static void thenCombine() throws InterruptedException, ExecutionException {
        System.out.println("\nCombinationTestApp.thenCombine");
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        /**
         * public <U,V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn)
         * - 연관이 없는 두 작업을 독립적으로 실행하고 두 작업이 모두 종료되었을 때 콜백 실행
         */
        CompletableFuture<String> future = hello.thenCombine(world, (h, w) -> h + " " + w);
        System.out.println(future.get());
    }

    private static void allOf() throws InterruptedException, ExecutionException {
        System.out.println("\nCombinationTestApp.allOf");
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        /**
         * public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
         * - 여러 작업을 모두 실행하고 모든 작업 결과에 콜백 실행
         */
        CompletableFuture[] futures = {hello, world};
        CompletableFuture<List<Object>> results = CompletableFuture.allOf(futures)
                .thenApply(v -> Arrays.stream(futures)
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));

        results.get().forEach(System.out::println);
    }

    private static void anyOf() throws InterruptedException, ExecutionException {
        System.out.println("\nCombinationTestApp.anyOf");
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        /**
         *  public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
         *  - 여러 작업 중에 가장 빨리 종료된 하나의 결과에 콜백 실행
         */
        CompletableFuture<Void> future = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
        future.get();
    }
}

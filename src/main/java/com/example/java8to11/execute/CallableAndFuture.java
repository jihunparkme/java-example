package com.example.java8to11.execute;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableAndFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        futureGet();
        futureClose();
        invokeAll();
        invokeAny();
    }

    private static void futureGet() throws InterruptedException, ExecutionException {
        System.out.println("CallableAndFuture.futureGet");
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Future<String> future = executorService.submit(hello);
        System.out.println("Started!");

        /**
         * boolean isDone(): 작업 상태 확인
         */
        System.out.println("isDone: " + future.isDone()); // isDone: false

        /**
         * V get(): 결과 가져오기
         *
         * - Blocking Call: 값을 가져올 때까지 대기
         * - timeout(최대 대기 시간) 설정 가능
         */
        future.get();

        System.out.println("isDone: " + future.isDone()); // isDone: true
        System.out.println("End!!");
        executorService.shutdown();
    }

    private static void futureClose() throws InterruptedException, ExecutionException {
        System.out.println("\nCallableAndFuture.futureClose");
        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Future<String> future = executorService.submit(hello);
        System.out.println("Started!");
        System.out.println("isDone: " + future.isDone()); // isDone: false

        /**
         * boolean cancel(boolean mayInterruptIfRunning): 진행중인 작업을 interrupt 요청으로 종료
         * - parameter
         *   - true: 현재 진행중인 쓰레드를 interrupt
         *   - false: 현재 진행중인 작업이 끝날때까지 대기
         * - 취소 했으면 true 못 했으면 false 리턴
         * - 취소 이후에 get() 요청 시 CancellationException 발생
         */
        boolean cancel = future.cancel(true);
        System.out.println("isCancel: " + cancel); // isCancel: true
        System.out.println("isDone: " + future.isDone()); // isDone: true
        System.out.println("End!!");
        executorService.shutdown();
    }

    private static void invokeAll() throws InterruptedException, ExecutionException {
        System.out.println("\nCallableAndFuture.invokeAll");
        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> the = () -> {
            Thread.sleep(3000L);
            return "the";
        };

        Callable<String> java = () -> {
            Thread.sleep(1000L);
            return "java";
        };

        /**
         * <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
         * - 동시에 실행(가장 오래 걸리는 작업 만큼 시간 소요)
         */
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, the, java));
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        executorService.shutdown();
    }

    private static void invokeAny() throws InterruptedException, ExecutionException {
        System.out.println("\nCallableAndFuture.invokeAll");
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> the = () -> {
            Thread.sleep(3000L);
            return "the";
        };

        Callable<String> java = () -> {
            Thread.sleep(1000L);
            return "java";
        };

        /**
         * <T> T invokeAny(Collection<? extends Callable<T>> tasks)
         * - Blocking Call
         * - 동시 실행 작업 중 가장 짧게 소요되는 작업 만큼 시간 소요
         */
        String result = executorService.invokeAny(Arrays.asList(hello, the, java));
        System.out.println(result);

        executorService.shutdown();
    }
}

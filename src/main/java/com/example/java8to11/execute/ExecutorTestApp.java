package com.example.java8to11.execute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTestApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> System.out.println("Thread " + Thread.currentThread().getName()));

        /**
         * void shutdown(): 이전에 제출된 작업이 실행되지만 새 작업은 허용되지 않는 순차적 종료(Graceful Shutdown)
         * List<Runnable> shutdownNow(): 현재 실행 중인 모든 작업을 중지하려고 시도하고, 대기 중인 작업의 처리를 중지하고, 실행 대기 중인 작업 목록을 반환
         */
        executorService.shutdown();
    }
}

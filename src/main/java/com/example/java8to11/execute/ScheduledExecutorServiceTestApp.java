package com.example.java8to11.execute;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTestApp {
    public static void main(String[] args) {
        schedule();
        scheduleAtFixedRate();
    }

    private static void schedule() {
        System.out.println("ScheduledExecutorService: schedule");
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() ->
                        System.out.println("Thread " + Thread.currentThread().getName()),
                5,
                TimeUnit.SECONDS);

        executorService.shutdown();
        System.out.println("ScheduledExecutorService: shutdown");
    }

    private static void scheduleAtFixedRate() {
        System.out.println("scheduleAtFixedRate: scheduleAtFixedRate");
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() ->
                        System.out.println("Thread " + Thread.currentThread().getName()),
                1,
                2,
                TimeUnit.SECONDS);
    }
}

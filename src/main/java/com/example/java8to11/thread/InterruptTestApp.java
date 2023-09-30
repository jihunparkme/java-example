package com.example.java8to11.thread;

public class InterruptTestApp {
    /**
     * hello : main
     * world : Thread-0
     * world : Thread-0
     * world : Thread-0
     * interrupted!
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("world : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("interrupted!");
                    return;
                }
            }
        });
        thread.start();

        System.out.println("hello : " + Thread.currentThread().getName());
        Thread.sleep(3000L);
        thread.interrupt();
    }
}

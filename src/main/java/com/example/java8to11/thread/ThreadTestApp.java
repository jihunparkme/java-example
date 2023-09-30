package com.example.java8to11.thread;

public class ThreadTestApp {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("world : " + Thread.currentThread().getName());
        });
        thread.start();

        System.out.println("hello : " + Thread.currentThread().getName());
    }
}

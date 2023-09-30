package com.example.java8to11.thread;

public class JoinTestApp {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("world : " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        thread.start();

        System.out.println("hello : " + Thread.currentThread().getName());
        try {
            thread.join(); // thread 대기
        } catch (InterruptedException e) {
            e.printStackTrace(); // thread 대기 중 main thread interrupt 요청 시 예외 발생
        }
        System.out.println(thread + " is finished");
    }
}

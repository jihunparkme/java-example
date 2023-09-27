package com.example.java8to11.functionalInterface;

@FunctionalInterface
public interface RunSomething {
    // 추상 메서드가 하나만 있으면 함수형 인터페이스
    void doIt();

    static void printName() {
        System.out.println("Aaron");
    }

    default void printAge() {
        System.out.println("30");
    }
}

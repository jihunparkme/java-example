package com.example.java8to11.functionalInterface;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class ConsumerTest {

    @Test
    void accept() throws Exception {
        /**
         * void accept(T t);
         */
        Consumer<Integer> printT = System.out::println;
        printT.accept(10); // 10
    }

    @Test
    void andThen() throws Exception {
        /**
         * Consumer<T> andThen(Consumer<? super T> after)
         */
        Consumer<String> printJava = s -> System.out.println(s + "Java ");
        Consumer<String> printWorld = s -> System.out.println(s + "World ");;
        printJava.andThen(printWorld).accept("Hello"); // HelloJava -> HelloWorld
    }
}

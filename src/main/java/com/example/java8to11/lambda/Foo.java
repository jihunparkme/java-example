package com.example.java8to11.lambda;

import java.util.function.Function;

public class Foo {
    public static void main(String[] args) {
        Function<Integer, Integer> plus10 = (num) -> num + 10;
        Function<Integer, Integer> multiply2 = (num) -> num * 2;

        /**
         * R apply(T t)
         */
        System.out.println(plus10.apply(1));

        /**
         * Function<V, R> compose(Function<? super V, ? extends T> before)
         * 함수 조합용 메소드
         */
        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
        System.out.println(multiply2AndPlus10.apply(2)); // (num * 2) + 10

        /**
         * Function<T, V> andThen(Function<? super R, ? extends V> after)
         * 함수 조합용 메소드
         */
        Function<Integer, Integer> plus10AndMultiply2 = plus10.andThen(multiply2);
        System.out.println(plus10AndMultiply2.apply(2));  // (num + 10) * 2
    }
}

package com.example.java8to11.functionalInterface;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

class FunctionTest {

    private final Function<Integer, Integer> plus10 = (num) -> num + 10;
    private final Function<Integer, Integer> multiply2 = (num) -> num * 2;

    @Test
    void apply() throws Exception {
        /**
         * R apply(T t)
         */
        Assertions.assertEquals(11, plus10.apply(1));
    }

    @Test
    void compose() throws Exception {
        /**
         * Function<V, R> compose(Function<? super V, ? extends T> before)
         * multiply2 실행 이후 plus10 실행
         */
        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
        Assertions.assertEquals(14, multiply2AndPlus10.apply(2)); // (num * 2) + 10
    }

    @Test
    void andThen() throws Exception {
        /**
         * Function<T, V> andThen(Function<? super R, ? extends V> after)
         * plus10 실행 이후 multiply2 실행
         */
        Function<Integer, Integer> plus10AndMultiply2 = plus10.andThen(multiply2);
        Assertions.assertEquals(24, plus10AndMultiply2.apply(2)); // (num + 10) * 2
    }
}
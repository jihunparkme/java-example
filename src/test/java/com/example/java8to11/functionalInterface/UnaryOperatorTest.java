package com.example.java8to11.functionalInterface;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.UnaryOperator;

public class UnaryOperatorTest {

    private final UnaryOperator<Integer> plus10 = (num) -> num + 10;
    private final UnaryOperator<Integer> multiply2 = (num) -> num * 2;

    @Test
    void test() throws Exception {
        Assertions.assertEquals(11, plus10.apply(1));
        Assertions.assertEquals(14, plus10.compose(multiply2).apply(2)); // (num * 2) + 10
        Assertions.assertEquals(24, plus10.andThen(multiply2).apply(2)); // (num + 10) * 2
    }
}

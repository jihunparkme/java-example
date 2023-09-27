package com.example.java8to11.functionalInterface;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;

public class BinaryOperatorTest {
    @Test
    void apply() throws Exception {
        /**
         * R apply(T t, U u);
         */
        BinaryOperator<Integer> add = (num1, num2) ->  num1 + num2;
        BinaryOperator<Integer> minus = (num1, num2) -> num1 - num2;
        BinaryOperator<Integer> multiple = (num1, num2) -> num1 * num2;

        Assertions.assertEquals(15, add.apply(10, 5));
        Assertions.assertEquals(5, minus.apply(10, 5));
        Assertions.assertEquals(50, multiple.apply(10, 5));
    }
}

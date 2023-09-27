package com.example.java8to11.functionalInterface;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

public class PredicateTest {

    private final Predicate<Integer> isEven = i -> i % 2 == 0;
    private final Predicate<Integer> under10 = i -> i < 10;

    @Test
    void test() throws Exception {
        /**
         * boolean test(T t);
         */
        Predicate<String> startsWithHello = s -> s.startsWith("hello");

        Assertions.assertTrue(startsWithHello.test("hello Aaron"));
        Assertions.assertTrue(isEven.test(8));
    }

    @Test
    void and() throws Exception {
        /**
         * Predicate<T> and(Predicate<? super T> other)
         */
        Assertions.assertTrue(isEven.and(under10).test(4));
        Assertions.assertFalse(isEven.and(under10).test(12));
    }

    @Test
    void or() throws Exception {
        /**
         * Predicate<T> or(Predicate<? super T> other)
         */
        Assertions.assertTrue(isEven.or(under10).test(4));
        Assertions.assertTrue(isEven.or(under10).test(12));
        Assertions.assertTrue(isEven.or(under10).test(7));
    }

    @Test
    void negate() throws Exception {
        /**
         * Predicate<T> negate()
         */
        Assertions.assertTrue(isEven.negate().test(5));
        Assertions.assertTrue(under10.negate().test(17));
        Assertions.assertFalse(isEven.negate().test(4));
        Assertions.assertFalse(under10.negate().test(5));
    }
}

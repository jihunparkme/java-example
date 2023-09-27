package com.example.java8to11.functionalInterface;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

public class SupplierTest {

    @Test
    void get() throws Exception {
        /**
         * T get()
         */
        Supplier<Integer> get10 = () -> 10;
        Assertions.assertEquals(10, get10.get());
    }
}

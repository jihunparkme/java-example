package com.example.java8to11.defaultMethods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionTest {
    private List<String> name = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        name.add("park");
        name.add("aaron");
        name.add("keesun");
        name.add("whiteship");
    }

    @Test
    void stream() throws Exception {
        /**
         * default Stream<E> stream()
         */
        long count = name.stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("A"))
                .count();

        Assertions.assertEquals(1, count);
    }

    @Test
    void removeIf() throws Exception {
        /**
         * default Stream<E> stream()
         */
        name.removeIf(s -> s.startsWith("w"));
        Assertions.assertEquals(3, name.size());
    }
}

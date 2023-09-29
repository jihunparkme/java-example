package com.example.java8to11.defaultMethods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {
    private List<String> name = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        name.add("park");
        name.add("aaron");
        name.add("keesun");
        name.add("whiteship");
    }

    @Test
    void sort() throws Exception {
        /**
         * default void sort(Comparator<? super E> c)
         */
        // 순차정렬
        name.sort(String::compareToIgnoreCase);

        // 역순정렬
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        name.sort(compareToIgnoreCase.reversed());
    }
}

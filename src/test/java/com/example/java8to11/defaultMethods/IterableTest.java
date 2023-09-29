package com.example.java8to11.defaultMethods;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Spliterator;

public class IterableTest {

    private static final List<String> name = List.of("park", "aaron", "keesun", "whiteship");

    @Test
    void forEach() throws Exception {
        /**
         * default void forEach(Consumer<? super T> action)
         * - 모든 요소가 처리되거나 예외가 발생할 때까지 Iterable 각 요소에 대해 지정된 작업 수행
         */
        name.forEach(System.out::println);
    }

    @Test
    void spliterator() throws Exception {
        /**
         * default Spliterator<E> spliterator()
         * - Creates a Spliterator over the elements described by this Iterable.
         */
        Spliterator<String> spliterator1 = name.spliterator();
        Spliterator<String> spliterator2 = spliterator1.trySplit();
        while(spliterator1.tryAdvance(System.out::println)); // keesun, whiteship
        while(spliterator2.tryAdvance(System.out::println)); // park, aaron
    }
}

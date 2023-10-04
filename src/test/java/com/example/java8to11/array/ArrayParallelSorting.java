package com.example.java8to11.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ArrayParallelSorting {
    @Test
    void method() throws Exception {
        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();

        /**
         * Dual-Pivot Quicksort.
         * 알고리듬 효율성은 동일. 시간 O(n log(n)) 공간 O(n)
         */
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        long start = System.nanoTime();
        Arrays.sort(numbers);
        System.out.println("serial sorting took " + (System.nanoTime() - start)); // 629500

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers);
        System.out.println("parallel sorting took " + (System.nanoTime() - start)); // 400375
    }
}

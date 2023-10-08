package com.example.java8to11.reflection;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ReflectionAnnotationTest {

    @Test
    void getAnnotations() throws Exception {
        Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);
    }

    @Test
    void getSuperClassAnnotations() throws Exception {
        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);
    }

    @Test
    void getCurrentAnnotations() throws Exception {
        Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println);
    }

    @Test
    void getAnnotatedFieldValue() throws Exception {
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            Arrays.stream(f.getAnnotations()).forEach(a -> {
                MyAnotherAnnotation myAnotherAnnotation = (MyAnotherAnnotation) a;
                System.out.println(f);
                System.out.println(myAnotherAnnotation.value());
            });
        });
    }
}
package com.example.java8to11.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

class ReflectionTest {
    @Test
    void getClassTypeInstance() throws ClassNotFoundException {
        // 클래스 타입 인스튼스 가져오기
        Class<Book> bookClass = Book.class;

        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        Class<?> aClass1 = Class.forName("com.example.java8to11.reflection.Book");
    }

    @Test
    void getField() throws Exception {
        Class<Book> bookClass = Book.class;

        System.out.println("return public fields");
        Arrays.stream(bookClass.getFields()).forEach(System.out::println);

        System.out.println("\nreturn all fields");
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);

        System.out.println("\nget fields value");
        Book book = new Book();
        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true);
                System.out.printf("%s %s\n", f, f.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    void getFieldsValue() throws Exception {
        Class<Book> bookClass = Book.class;

        Book book = new Book();
        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true);
                System.out.printf("%s %s\n", f, f.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    void getMethods() throws Exception {
        Arrays.stream(Book.class.getMethods()).forEach(System.out::println);
    }

    @Test
    void getConstructors() throws Exception {
        Arrays.stream(Book.class.getDeclaredConstructors()).forEach(System.out::println);
    }

    @Test
    void getSuperClass() throws Exception {
        System.out.println(MyBook.class.getSuperclass());
    }

    @Test
    void getInterfaces() throws Exception {
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);
    }

    @Test
    void getFieldsFeature() throws Exception {
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            int modifiers = f.getModifiers();
            System.out.println(f);
            System.out.println("isPrivate: " + Modifier.isPrivate(modifiers));
            System.out.println("isStatic: " + Modifier.isStatic(modifiers));
        });
    }

    @Test
    void getMethodsFeature() throws Exception {
        Arrays.stream(Book.class.getMethods()).forEach(m -> {
            System.out.println(m);
            System.out.println(m.getReturnType());
            System.out.println(m.getParameterCount());
        });
    }
}
package com.example.java8to11.reflection;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@MyAnnotation(number = 100)
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private static String B = "BOOK";
    private static final String C = "BOOK";

    @MyAnotherAnnotation("aaron")
    private String a = "a";
    public String d = "d";
    protected String e = "e";

    public void f() {
        System.out.println("F");
    }

    public void g() {
        System.out.println("G");
    }

    public int h() {
        return 100;
    }
}

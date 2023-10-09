package com.example.java8to11.reflection;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Ball {
    public static String A = "A";

    private String b = "B";

    public  Ball(String b) {
        this.b = b;
    }

    private void c() {
        System.out.println("c");
    }

    public int sum(int left, int right) {
        return left + right;
    }
}

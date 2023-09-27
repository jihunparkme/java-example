package com.example.java8to11.lambda;

import com.example.java8to11.functionalInterface.RunSomething;

public class Foo {
    public static void main(String[] args) {
        RunSomething runSomething = () -> System.out.println("Hello");
        runSomething.doIt();
    }
}

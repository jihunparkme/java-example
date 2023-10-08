package com.example.java8to11.reflection;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnotherAnnotation {
    String value();
}

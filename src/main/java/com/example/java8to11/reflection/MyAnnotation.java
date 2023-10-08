package com.example.java8to11.reflection;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Inherited
public @interface MyAnnotation {
    String name() default "aaron";
    int number();
}

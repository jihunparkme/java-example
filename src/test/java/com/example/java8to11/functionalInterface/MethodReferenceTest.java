package com.example.java8to11.functionalInterface;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodReferenceTest {

    @Test
    void static_method_reference() throws Exception {
        // static method reference(Type::static-method)
        UnaryOperator<String> hi = Greeting::hi;
        assertEquals("hi aaron", hi.apply("aaron"));
    }

    @Test
    void random_object_instance_method_reference() throws Exception {
        String[] names = {"ccc", "aaa", "bbb"};
        // random object instance method reference(Type::instance-method)
        Arrays.sort(names, String::compareToIgnoreCase);
        assertEquals("[aaa, bbb, ccc]", Arrays.toString(names));
    }

    @Test
    void no_arg_constructor_reference() throws Exception {
        // no arg constructor reference(Type::new)
        Supplier<Greeting> greetingSupplier = Greeting::new;
        Greeting greeting = greetingSupplier.get();
        // specific object instance method reference(Object-reference::instance-method)
        UnaryOperator<String> hello = greeting::hello;

        assertEquals("Hello Aaron", hello.apply("Aaron"));
    }

    @Test
    void AllArgsConstructor() throws Exception {
        // arg constructor reference(Type::new)
        Function<String, Greeting> greetingFunction = Greeting::new;
        Greeting greeting = greetingFunction.apply("aaron");
        assertEquals("aaron", greeting.getName());
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private class Greeting {
        private String name;

        public String hello(String name) {
            return "Hello " + name;
        }

        public static String hi(String name) {
            return "hi " + name;
        }
    }
}

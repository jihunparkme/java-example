package com.example.java8to11.defaultMethods;

import java.util.Locale;

public interface DefaultMethodInterface {
    void printName();

    String getName();

    /**
     * @implSpec
     * 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력.ㅊ
     */
    default void pringNameUpperCase() {
        System.out.println(getName().toUpperCase(Locale.ROOT));
    }

    static void printAnything() {
        System.out.println("this is static method");
    }
}

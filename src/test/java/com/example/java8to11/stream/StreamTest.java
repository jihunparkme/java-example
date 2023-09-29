package com.example.java8to11.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    private List<OnlineClass> springClasses = new ArrayList<>();
    private List<OnlineClass> javaClasses = new ArrayList<>();
    private List<List<OnlineClass>> aaronEvents = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpaa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "spring api development", false));

        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", true));

        aaronEvents.add(springClasses);
        aaronEvents.add(javaClasses);
    }

    @Test
    @DisplayName("spring 으로 시작하는 수업")
    void test01() {
        List<OnlineClass> springClass = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .collect(Collectors.toList());
        Assertions.assertEquals(5, springClass.size());
    }

    @Test
    @DisplayName("close 되지 않은 수업")
    void test02() {
        List<OnlineClass> closedClass = springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed))
                .collect(Collectors.toList());
        Assertions.assertEquals(3, closedClass.size());
    }

    @Test
    @DisplayName("수업 이름만 모아서 스트림 만들기")
    void test03() {
        springClasses.stream()
                .map(OnlineClass::getTitle)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("두 수업 목록에 들어 있는 모든 수업 아이디")
    void test04() {
        List<OnlineClass> allClasses = aaronEvents.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        Assertions.assertEquals(8, allClasses.size());
    }

    @Test
    @DisplayName("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만")
    void test05() {
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("자바 수업 중 Test가 들어 있는 수업이 있는지 확인")
    void test06() {
        boolean result = javaClasses.stream()
                .anyMatch(oc -> oc.getTitle().contains("Test"));
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("스프링 수업 중에 제목에 spring 이 들어간 제목만 모아서 List 로 만들기")
    void test07() {
        List<String> result = springClasses.stream()
                .filter(oc -> oc.getTitle().contains("spring"))
                .map(oc -> oc.getTitle())
                .collect(Collectors.toList());
        Assertions.assertEquals(5, result.size());
    }
}

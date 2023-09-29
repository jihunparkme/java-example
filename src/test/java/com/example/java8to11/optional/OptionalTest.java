package com.example.java8to11.optional;

import com.example.java8to11.stream.OnlineClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionalTest {

    private List<OnlineClass> springClasses = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "spring api development", false));
    }

    @Test
    @DisplayName("Optional 에 값이 존재하는지 확인")
    void isPresent() {
        Optional<OnlineClass> result = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("Optional 에 값이 존재할 경우 동작 수행")
    void ifPresent() {
        Optional<OnlineClass> result = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        result.ifPresent(oc -> System.out.println(oc.getTitle()));
    }

    @Test
    @DisplayName("Optional 이 비어있는지 확인")
    void isEmpty() {
        Optional<OnlineClass> result = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("빈 Optional get() 요청 시 NoSuchElementException 예외 발생")
    void get() {
        Optional<OnlineClass> result = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();

        assertThrows(NoSuchElementException.class, () -> {
            result.get();
        });
    }

    @Test
    @DisplayName("값이 있을 경우 꺼내고, 무조건 새로운 클래스 생성")
    void orElse() {
        Optional<OnlineClass> result = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        result.orElse(createNewClass());
    }

    @Test
    @DisplayName("값이 있을 경우 꺼내고, 없으면 새로운 클래스 제공")
    void orElseGet() {
        Optional<OnlineClass> result = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        result.orElseGet(OptionalTest::createNewClass);
    }

    @Test
    @DisplayName("값이 있을 경우 꺼내고, 없으면 예외")
    void orElseThrow() {
        Optional<OnlineClass> result = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();

        assertThrows(NoSuchElementException.class, () -> {
            result.orElseThrow();
        });

        assertThrows(IllegalStateException.class, () -> {
            result.orElseThrow(IllegalStateException::new);
        });
    }

    @Test
    @DisplayName("Optional 값을 필터링")
    void filter() {
        Optional<OnlineClass> result = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        Optional<OnlineClass> springClass = result.filter(OnlineClass::isClosed);
        assertTrue(springClass.isPresent());

        result = springClasses.stream()
                        .filter(oc -> oc.getTitle().startsWith("jpa"))
                        .findFirst();

        Optional<OnlineClass> jpaClass = result.filter(Predicate.not(OnlineClass::isClosed));
        assertTrue(jpaClass.isEmpty());
    }

    @Test
    @DisplayName("Optional 값을 매핑")
    void map() {
        Optional<OnlineClass> result = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        Optional<Integer> springClassId = result.map(OnlineClass::getId);
        assertTrue(springClassId.isPresent());

        result = springClasses.stream()
                        .filter(oc -> oc.getTitle().startsWith("jpa"))
                        .findFirst();

        Optional<Integer> jpaClassId = result.map(OnlineClass::getId);
        assertTrue(jpaClassId.isEmpty());
    }

    private static OnlineClass createNewClass() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "jpa Class", false);
    }
}

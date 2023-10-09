package com.example.java8to11.di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {
    /**
     * classType 에 해당하는 타입의 객체 생성
     * - 해당 객체의 필드 중에 @Inject 가 있다면 해당 필드도 같이 만들어 제공
     */
    public static <T> T getObject(Class<T> classType) {
        T instance = createInstance(classType);
        Arrays.stream(classType.getDeclaredFields()).forEach(f -> {
            // @Inject 선언 필드 탐색
            if (f.getAnnotation(Inject.class) != null) {
                Object fieldInstance = createInstance(f.getType());
                f.setAccessible(true);
                try {
                    //  @Inject 선언 필드에 인스턴스 주입
                    f.set(instance, fieldInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException();
                }
            }
        });

        return instance;
    }

    private static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor(null).newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException();
        }
    }
}

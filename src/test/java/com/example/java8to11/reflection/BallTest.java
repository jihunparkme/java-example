package com.example.java8to11.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class BallTest {
    @Test
    void generateClassInstance() throws Exception {
        Class<Ball> ballClass = Ball.class;
//        ballClass.newInstance();
        Constructor<Ball> constructor = ballClass.getConstructor(String.class);
        Ball ball = constructor.newInstance("myBall");
        System.out.println(ball);
    }

    @Test
    void editClassField() throws Exception {
        Field field = Ball.class.getDeclaredField("A");
        // public static 은 특정한 인스턴스에 해당하는 것이 아니라 클래스에 해당하므로 null 전달
        System.out.println(field.get(null));
        field.set(null, "newA");
        System.out.println(field.get(null));
    }

    @Test
    void editInstanceField() throws Exception {
        Class<Ball> ballClass = Ball.class;
        Constructor<Ball> constructor = ballClass.getConstructor(String.class);
        Ball ball = constructor.newInstance("b");

        Field field = Ball.class.getDeclaredField("b");
        field.setAccessible(true); // private 필드 접근을 위한 설정
        System.out.println(field.get(ball));
        field.set(ball, "newB");
        System.out.println(field.get(ball));
    }

    @Test
    void runPrivateMethod() throws Exception {
        Class<Ball> ballClass = Ball.class;
        Constructor<Ball> constructor = ballClass.getConstructor(String.class);
        Ball ball = constructor.newInstance("myBall");

        Method method = Ball.class.getDeclaredMethod("c");
        method.setAccessible(true);
        method.invoke(ball);
    }

    @Test
    void runPublicMethod() throws Exception {
        Class<Ball> ballClass = Ball.class;
        Constructor<Ball> constructor = ballClass.getConstructor(String.class);
        Ball ball = constructor.newInstance("myBall");

        Method method = Ball.class.getDeclaredMethod("sum", int.class, int.class);
        int invoke = (int) method.invoke(ball, 1, 2);
        System.out.println(invoke);
    }
}
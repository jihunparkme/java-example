package com.example.java8to11.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static net.bytebuddy.matcher.ElementMatchers.named;

class BookServiceTest {

    BookService defaultBookService = (BookService) Proxy.newProxyInstance(
            BookService.class.getClassLoader(),
            new Class[]{BookService.class}, // 어떤 인텉페이스 타입의 구현체인지
            new InvocationHandler() { // 프록시에 어떤 메소드가 호출이 될 때 그 메소드 호출을 어떻게 처리할지에 대한 설명
                BookService bookService = new DefaultBookService();

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (method.getName().equals("rent")) {
                        System.out.println("qqqqq");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("zzzzz");
                        return invoke;
                    }
                    return method.invoke(bookService, args);
                }
            }
    );

    @Test
    void proxy() throws Exception {
        Book book = new Book();
        book.setTitle("spring");
        defaultBookService.rent(book);
    }

    @Test
    void cglib_proxy() throws Exception {
        MethodInterceptor handler = new MethodInterceptor() {
            BallService bookService = new BallService();
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals("rent")) {
                    System.out.println("bbbbb");
                    Object invoke = method.invoke(bookService, args);
                    System.out.println("ccccc");
                    return invoke;
                }
                return method.invoke(bookService, args);
            }
        };
        BallService ballService = (BallService) Enhancer.create(BallService.class, handler);

        Book book = new Book();
        book.setTitle("spring");
        ballService.rent(book);
    }

    @Test
    void byteBuddy_proxy() throws Exception {
        Class<? extends BallService> proxyClass = new ByteBuddy().subclass(BallService.class)
                .method(named("rent")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    BallService bookService = new BallService();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("qqqq");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("eeee");
                        return invoke;
                    }
                }))
                .make().load(BallService.class.getClassLoader()).getLoaded();
        BallService ballService = proxyClass.getConstructor(null).newInstance();

        Book book = new Book();
        book.setTitle("spring");
        ballService.rent(book);
    }
}
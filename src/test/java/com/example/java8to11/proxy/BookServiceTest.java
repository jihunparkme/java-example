package com.example.java8to11.proxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
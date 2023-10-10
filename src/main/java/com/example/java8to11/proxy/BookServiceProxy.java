package com.example.java8to11.proxy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookServiceProxy implements BookService {

    private final BookService bookService;

    @Override
    public void rent(Book book) {
        System.out.println("aaaa");
        bookService.rent(book);
        System.out.println("bbbb");
    }
}

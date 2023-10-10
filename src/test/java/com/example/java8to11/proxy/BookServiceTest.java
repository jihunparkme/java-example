package com.example.java8to11.proxy;

import org.junit.jupiter.api.Test;

class BookServiceTest {

    BookService defaultBookService = new BookServiceProxy(new DefaultBookService());

    @Test
    void proxy() throws Exception {
        Book book = new Book();
        book.setTitle("spring");
        defaultBookService.rent(book);
    }
}
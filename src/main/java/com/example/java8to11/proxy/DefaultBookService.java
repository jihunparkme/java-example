package com.example.java8to11.proxy;

public class DefaultBookService implements BookService {

    public void rent(Book book) {
        System.out.println("rent: " + book.getTitle());
    }
}

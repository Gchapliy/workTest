package com.example.service;

import com.example.entity.Book;

import java.util.Set;

public interface BookService {
    void saveBook(Book book);

    Set<Book> findAll();
}

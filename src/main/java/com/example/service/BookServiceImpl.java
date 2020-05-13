package com.example.service;

import com.example.dao.BookDAO;
import com.example.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    public void saveBook(Book book) {
        bookDAO.save(book);
    }

    @Override
    public Set<Book> findAll() {
        return new HashSet<>(bookDAO.findAll());
    }
}

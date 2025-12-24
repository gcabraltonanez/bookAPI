package com.example.booksAPI.services;

import java.util.List;

import com.example.booksAPI.models.Book;

public interface IBookService {
    List<Book> getAllBooks();

    Book createBook(Book book);

    Book updateBook(Book book);

    Book getBookByTitle(String title);

    Book getBookById(Long id);

    void deleteBook(Long id);

    void deleteAllBooks();
    
}

package com.example.booksAPI.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.booksAPI.models.Book;

public interface IBookService {
    public ArrayList<Book> getAllBooks();
    public Book createBook(Book book);
    public Book updateBook(Book book);
    public Optional<Book> findBookByTitle(String title);
    public Optional<Book> findBookById(Long id);
    public Book deleteBook(Long id);
    public void deleteAllBooks();
    
}

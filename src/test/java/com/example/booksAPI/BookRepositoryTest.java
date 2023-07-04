package com.example.booksAPI;

import com.example.booksAPI.models.Book;
import com.example.booksAPI.repositories.BookRepository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = BooksAPIApplication.class)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    private Book getBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Rayuela");
        book.setAuthor("Julio Cortázar");
        book.setPrice(1000);
        book.setReleaseDate("28-06-1963");
        return book;
    }
}

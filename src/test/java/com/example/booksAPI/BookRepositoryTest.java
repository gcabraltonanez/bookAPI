package com.example.booksAPI;

import com.example.booksAPI.models.Book;
import com.example.booksAPI.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = BooksAPIApplication.class)
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void savesAndFindsByTitle() {
        Book book = getBook();
        bookRepository.save(book);

        assertThat(bookRepository.findByTitle("Rayuela")).isPresent();
    }

    private Book getBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Rayuela");
        book.setAuthor("Julio Cortázar");
        book.setPrice(new BigDecimal("1000.00"));
        book.setReleaseDate(LocalDate.of(1963, 6, 28));
        return book;
    }
}

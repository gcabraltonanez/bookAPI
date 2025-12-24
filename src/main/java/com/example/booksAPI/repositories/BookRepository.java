package com.example.booksAPI.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.booksAPI.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public abstract Optional<Book> findByTitle(String title);

}

package com.example.booksAPI.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.booksAPI.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    public abstract Optional<Book> findByTitle(String title);

}

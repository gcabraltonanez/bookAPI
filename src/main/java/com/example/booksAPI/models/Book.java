package com.example.booksAPI.models;

import com.example.booksAPI.validators.BooksDataValidator;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotEmpty(message = BooksDataValidator.TITLE)
    @Column(unique = true)
    private String title;

    @NotEmpty(message = BooksDataValidator.AUTHOR)
    @Column(nullable = false)
    private String author;

    @Positive(message = BooksDataValidator.PRICE)
    @Column(nullable = false)
    private Integer price;
    
    @NotEmpty(message = BooksDataValidator.RELEASE_DATE)
    @Column(nullable = false)
    private String releaseDate;

    public Book(){}

    public Book(Long id, String title, String author, Integer price, String releaseDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.releaseDate = releaseDate;
    }
    
}

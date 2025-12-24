package com.example.booksAPI.models;

import com.example.booksAPI.validators.BooksDataValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotBlank(message = BooksDataValidator.TITLE)
    @Column(unique = true)
    private String title;

    @NotBlank(message = BooksDataValidator.AUTHOR)
    @Pattern(regexp = "^[\\p{L}][\\p{L}\\p{M} .'-]{1,100}$", message = BooksDataValidator.AUTHOR_PATTERN)
    @Column(nullable = false)
    private String author;

    @NotNull(message = BooksDataValidator.PRICE_EMPTY)
    @DecimalMin(value = "0.01", inclusive = true, message = BooksDataValidator.PRICE)
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @NotNull(message = BooksDataValidator.RELEASE_DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate releaseDate;

    public Book(){}

    public Book(Long id, String title, String author, BigDecimal price, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.releaseDate = releaseDate;
    }
    
}

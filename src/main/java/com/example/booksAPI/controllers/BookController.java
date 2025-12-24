package com.example.booksAPI.controllers;

import com.example.booksAPI.dto.ApiMessage;
import com.example.booksAPI.models.Book;
import com.example.booksAPI.services.IBookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/books")
public class BookController {

    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book created = this.bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiMessage> updateBook(@Valid @RequestBody Book book) {
        Book updatedBook = this.bookService.updateBook(book);
        return ResponseEntity.ok(new ApiMessage("Se modificó el libro con id " + updatedBook.getId()));
    }
    
    @GetMapping("/find")
    public ResponseEntity<Book> findBookByTitle(@RequestParam(required = false) String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El título a buscar no puede estar vacío");
        }

        Book foundBook = bookService.getBookByTitle(title);
        return ResponseEntity.ok(foundBook);
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Book> findOne(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ApiMessage> deleteOneBookById(@PathVariable("bookId") Long id) {
        this.bookService.deleteBook(id);
        return ResponseEntity.ok(new ApiMessage("Se eliminó el libro con id " + id));
    }
    
    @DeleteMapping("/deleteAll")
    public ResponseEntity<ApiMessage> deleteAllBooks() {

        int booksSize = this.bookService.getAllBooks().size();

        // antes de borrar todos los libros se chequea que la base tenga cargado al menos uno
        if (booksSize == 0) {
            return ResponseEntity.badRequest().body(new ApiMessage("Lista de libros vacía"));
        }

        this.bookService.deleteAllBooks();
        return ResponseEntity.ok(new ApiMessage("Todos los libros fueron eliminados"));
    }
}

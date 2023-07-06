package com.example.booksAPI.controllers;

import java.util.*;

import javax.validation.Valid;

import com.example.booksAPI.helpers.BuildResponse;
import com.example.booksAPI.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.example.booksAPI.services.BookServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    @GetMapping("/all")
    public Object getBooks() {
        return bookService.getAllBooks().size() > 0 ? bookService.getAllBooks() : new ResponseEntity<> (BuildResponse.buildHTTPResponse
                (404, "No hay libros cargados"),
        HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createBook(@Valid @RequestBody Book book, Errors errors) {

        try {
            Optional<Book> optionalBook = bookService.findBookByTitle(book.getTitle());

            if(optionalBook.isEmpty()){
                return new ResponseEntity<>(this.bookService.createBook(book), HttpStatus.CREATED);
            }
            return new ResponseEntity<>
                    (BuildResponse.buildHTTPResponse
                            (400, "Ya existe un libro con el título "+ book.getTitle()),
                            HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            HashMap<String, String> mappedErrors = new HashMap<>();

            for (FieldError validation : errors.getFieldErrors()) {
                mappedErrors.put(validation.getField(), validation.getDefaultMessage());
            }

            return new ResponseEntity<>(mappedErrors, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateBook(@Valid @RequestBody Book book, Errors errors) {

        try {
            /*
            primero se chequea que no exista otro libro con el título que queremos modificar,
            si es así, se devuelve un mensaje con el error correspondiente.
            */
            Optional <Book> existTitle = this.bookService.findBookByTitle(book.getTitle());
            Optional <Book> existId = this.bookService.findBookById(book.getId());
            existTitle.ifPresent(value -> System.out.println(value.getTitle()));
            if(existTitle.isPresent() && (existId.isPresent() && !Objects.equals(existId.get().getId(), book.getId()))){
                return new ResponseEntity<>
                        (BuildResponse.buildHTTPResponse
                                (200, "Existe libro con el título " + book.getTitle()),
                                HttpStatus.BAD_REQUEST);
            }
            Book updatedBook = this.bookService.updateBook(book);
            if (updatedBook.getId() != null){
                this.bookService.createBook(book);
                    return new ResponseEntity<>
                            (BuildResponse.buildHTTPResponse
                                    (200, "Se modifico el libro con id " + book.getId()),
                                    HttpStatus.OK);
                }

            else {
                return new ResponseEntity<>
                        (BuildResponse.buildHTTPResponse
                                (404, "No existe libro con id " + book.getId()),
                                HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {

            HashMap<String, String> mappedErrors = new HashMap<>();

            for (FieldError validation : errors.getFieldErrors()) {
                mappedErrors.put(validation.getField(), validation.getDefaultMessage());
            }
            return new ResponseEntity<>(mappedErrors, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/find")
    public ResponseEntity<?> findBookByTitle(@RequestParam(required = false) String title) {

        Optional<Book> foundBook = bookService.findBookByTitle(title);

        /*
        búsqueda de un libro segun su título, si el request param se ingresa vacío, se envía
        un mensaje con el error correspondiente. Si no se encuentra ningún libro con dicho título,
        también.
        */
        if (title == null || title.isBlank()) {

            return new ResponseEntity<>(BuildResponse.buildHTTPResponse
                    (400, "El título a buscar no puede estar vacío"),
                    HttpStatus.BAD_REQUEST);

        }

        if (foundBook.isEmpty()) {
            return new ResponseEntity<>(BuildResponse.buildHTTPResponse
                    (404, "El título especificado no existe"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bookService.findBookByTitle(title).orElse(null), HttpStatus.OK);
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Optional<Book> optionalBook = bookService.findBookById(id);


        return optionalBook.isPresent() ? new ResponseEntity<>(optionalBook, HttpStatus.OK)
                : new ResponseEntity<> (BuildResponse.buildHTTPResponse
                        (404, "No existe libro con id "+ id),
                        HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<?> deleteOneBookById(@PathVariable("bookId") Long id) {
        Book book = this.bookService.deleteBook(id);

        if (book.getId() != null) {
            return new ResponseEntity<>
                    (BuildResponse.buildHTTPResponse
                            (200, "Se eliminó el libro con id " + id),
                            HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>
                    (BuildResponse.buildHTTPResponse
                            (404, book.getTitle()),
                            HttpStatus.NOT_FOUND);
        }

    }
    
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllBooks() {

        int booksSize = this.bookService.getAllBooks().size();

        //antes de borrar todos los libros se chequea que la base tenga cargado al menos uno
        if (booksSize > 0){
            this.bookService.deleteAllBooks();
            return new ResponseEntity<>
                    (BuildResponse.buildHTTPResponse
                            (200, "Todos los libros fueron eliminados"),
                            HttpStatus.OK);
        }

        else return  new ResponseEntity<>
                (BuildResponse.buildHTTPResponse
                        (400, "Lista de libros vacía"),
                        HttpStatus.BAD_REQUEST);
    }
}

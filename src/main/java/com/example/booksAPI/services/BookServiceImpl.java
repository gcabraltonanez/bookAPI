package com.example.booksAPI.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.booksAPI.models.Book;
import com.example.booksAPI.repositories.BookRepository;
import com.example.booksAPI.suppliers.NotFoundSupplier;

@Service
public class BookServiceImpl implements IBookService {
    
    @Autowired
    BookRepository bookRepository;

    @Override
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> allBooks = (ArrayList<Book>) bookRepository.findAll();

        return !allBooks.isEmpty() ? allBooks : new ArrayList<>();
    };

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    };

    @Override
    public Optional<Book> findBookByTitle(String titulo) {
        return bookRepository.findByTitle(titulo);
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book updateBook(Book book) {
        Optional <Book> optBook = bookRepository.findById(book.getId());

        if(optBook.isPresent()){
            return bookRepository.save(book);
        }

        return optBook.orElseGet(new NotFoundSupplier());
    }

    @Override
    public Book deleteBook(Long id) {
        Optional<Book> optBook = bookRepository.findById(id);

        if (optBook.isPresent()) {
            Book bookToDelete = optBook.get();
            bookRepository.deleteById(bookToDelete.getId());

            return bookToDelete;
        }

        return optBook.orElseGet(new NotFoundSupplier());
    }

    @Override
    public void deleteAllBooks() {
        this.bookRepository.deleteAll();
    }

}

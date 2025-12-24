package com.example.booksAPI.services;

import com.example.booksAPI.exceptions.DuplicateTitleException;
import com.example.booksAPI.exceptions.NotFoundException;
import com.example.booksAPI.models.Book;
import com.example.booksAPI.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book createBook(Book book) {
        validateDuplicateTitle(book.getTitle(), null);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        if (book.getId() == null) {
            throw new IllegalArgumentException("El id del libro es obligatorio para actualizar");
        }
        Book existing = bookRepository.findById(book.getId())
                .orElseThrow(() -> new NotFoundException("No existe libro con id " + book.getId()));

        validateDuplicateTitle(book.getTitle(), book.getId());

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setPrice(book.getPrice());
        existing.setReleaseDate(book.getReleaseDate());

        return bookRepository.save(existing);
    }

    @Override
    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("El título especificado no existe"));
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe libro con id " + id));
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe libro con id " + id));
        bookRepository.delete(existing);
    }

    @Override
    @Transactional
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

    private void validateDuplicateTitle(String title, Long currentId) {
        Optional<Book> existing = bookRepository.findByTitle(title);
        if (existing.isPresent() && (currentId == null || !existing.get().getId().equals(currentId))) {
            throw new DuplicateTitleException("Ya existe un libro con el título " + title);
        }
    }
}

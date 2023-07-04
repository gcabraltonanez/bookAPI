package com.example.booksAPI.suppliers;

import com.example.booksAPI.models.Book;
import java.util.function.Supplier;

public class NotFoundSupplier implements Supplier<Book> {

    @Override
    public Book get() {
        Book notFound = new Book(null, "No se encuentra libro con el id solicitado", null, null, null);
        return notFound;
    }

}

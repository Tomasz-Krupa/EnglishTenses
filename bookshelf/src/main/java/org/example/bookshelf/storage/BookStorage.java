package org.example.bookshelf.storage;

import org.example.bookshelf.type.Book;

import java.util.List;

public interface BookStorage {
    Book getBook(long id);
    List<Book> getAllBooks();
    void addBook(Book book);
}

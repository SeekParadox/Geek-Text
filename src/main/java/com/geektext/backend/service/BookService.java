package com.geektext.backend.service;

import com.geektext.backend.dao.BookRepository;
import com.geektext.backend.models.Book;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BookService {
     private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findBookById(String bookId) {
    Optional<Book> optionalBook = bookRepository.findById(bookId);
    return optionalBook.orElse(null);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}


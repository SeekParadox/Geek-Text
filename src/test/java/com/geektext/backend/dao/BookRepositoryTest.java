package com.geektext.backend.dao;

import com.geektext.backend.dao.repository.BookRepository;
import com.geektext.backend.models.Book;
import org.junit.jupiter.api.Test;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("test")
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void findBooksByGenreTest() {
        //setup
        List<Book> bookList = bookRepository.findBooksByGenreIgnoreCase("Children's Literature");
        List<Book> bookList2 = bookRepository.findBooksByGenreIgnoreCase("None");

        //expected
        for (Book book : bookList) assertEquals("Children's Literature", book.getGenre());
        assertEquals(0, bookList2.size());
    }

    @Test
    public void findByTop10Sellers() {
        //setup
        List<Book> bookList = bookRepository.findTop10Sellers();
        int lastSold = Integer.MAX_VALUE;

        //expected
        for (Book book : bookList) {
            assertTrue(lastSold >= book.getSold());
            lastSold = book.getSold();
        }
    }

    @Test
    public void findBookByRatingTest() {
        //setup
        List<Book> bookList = bookRepository.findBooksByRatingGreaterThanEqual(3);
        List<Book> bookList2 = bookRepository.findBooksByRatingGreaterThanEqual(2);
        List<Book> bookList3 = bookRepository.findBooksByRatingGreaterThanEqual(2);
        List<Book> bookList4 = bookRepository.findBooksByRatingGreaterThanEqual(100);

        //expected
        for (Book book : bookList) assertTrue(book.getRatings() >= 3);
        for (Book book : bookList2) assertTrue(book.getRatings() >= 2);
        for (Book book : bookList3) assertTrue(book.getRatings() >= 1);
        assertTrue(bookList4.isEmpty());
    }

    @Test
    public void findBookByPublisherTest() {
        //setup
        List<Book> bookList = bookRepository.findAllByPublisherIgnoreCase("Broadway Books");
        List<Book> bookList2 = bookRepository.findAllByPublisherIgnoreCase("Gillian Flynn");
        List<Book> bookList3 = bookRepository.findAllByPublisherIgnoreCase("Historical Fiction");
        List<Book> bookList4 = bookRepository.findAllByPublisherIgnoreCase("Null");

        //expected
        for (Book book : bookList) assertEquals("Broadway Books", book.getPublisher());
        for (Book book : bookList2) assertEquals("Gillian Flynn", book.getPublisher());
        for (Book book : bookList3) assertEquals("Historical Fiction", book.getPublisher());
        assertTrue(bookList4.isEmpty());
    }

}
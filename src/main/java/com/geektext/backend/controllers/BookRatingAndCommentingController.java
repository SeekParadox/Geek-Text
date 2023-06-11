package com.geektext.backend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

import com.geektext.backend.dao.BookRepository;
import com.geektext.backend.service.BookRatingAndCommentingService;

public class BookRatingAndCommentingController {
    private static final Logger logger = LoggerFactory.getLogger(BookRatingAndCommentingController.class);
    private final BookRatingAndCommentingService bookRatingAndCommentingService;
    private final BookRepository bookRepository;

    public BookRatingAndCommentingController(BookRatingAndCommentingService bookRatingAndCommentingService, BookRepository bookRepository){
        this.bookRatingAndCommentingService = bookRatingAndCommentingService;
        this.bookRepository = bookRepository;
    }

   
}

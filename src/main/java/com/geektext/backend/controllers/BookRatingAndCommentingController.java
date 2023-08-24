
package com.geektext.backend.controllers;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geektext.backend.models.Book;
import com.geektext.backend.models.Comment;
import com.geektext.backend.models.Rating;
import com.geektext.backend.service.BookService;


@Controller
@RequestMapping("api/book")
public class BookRatingAndCommentingController {

    private final BookService bookService;

    @Autowired
    public BookRatingAndCommentingController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Adds a rating to the bookID
     *
     * @param bookId
     * @param ratingValue
     * @param userId
     * @return a redirect to the home page.
     */
    @PostMapping("/{bookId}/rating")
    public ResponseEntity<String> createRating(
            @PathVariable String bookId,
            @RequestParam int ratingValue,
            @RequestParam String userId) {

        // Find the book by its ID
        Book book = bookService.findBookById(bookId);

        if (book == null)
            return ResponseEntity.notFound().build();

        // Create a new rating object with the current date
        Rating rating = new Rating(ratingValue, new Date());

        // Add the rating to the book
        book.getRatings().put(userId, rating);

        // Save the updated book
        bookService.saveBook(book);
        return ResponseEntity.ok("Rating added successfully");
    }

    /**
     *
     * @param bookId the ID of the book.
     * @param comment
     * @param userId
     * @return a redirect to the home page.
     */
    @PostMapping("/{bookId}/comments")
    public ResponseEntity<String> createComment(
            @PathVariable String bookId,
            @RequestParam String comment,
            @RequestParam String userId) {

        Book book = bookService.findBookById(bookId);

        if (book == null) // Return book not found
            // Create a new comment object with the current date
            return ResponseEntity.notFound().build();


        book
                .getComments()
                .put(userId, new Comment(comment, new Date()));

        // Save the updated book
        bookService.saveBook(book);
        return ResponseEntity.ok("Comment added successfully");
    }

    /**
     * Retrieves all comment of the bookID
     *
     * @param id the ID of the book
     * @return a redirect to the home page.
     */
    @GetMapping("/{bookId}/comments")
    public ResponseEntity<List<Comment>> getBookComments(@PathVariable String bookId) {
        // Find the book by its ID
        Book book = bookService.findBookById(bookId);

        return book != null ?
                ResponseEntity.ok(new ArrayList<>(book.getComments().values())) :
                ResponseEntity.notFound().build();
    }

    /**
     * Retrieves the average rating of the bookID
     *
     * @param id the ID of the book to be added.
     * @return a redirect to the home page.
     */
    @GetMapping("/{bookId}/rating/average")
    public ResponseEntity<Double> getBookAverageRating(@PathVariable String bookId) {
        Book book = bookService.findBookById(bookId);

        return book != null ?
                ResponseEntity.ok(calculateAverageRating(book)) :
                ResponseEntity.notFound().build();

    }

    private double calculateAverageRating(Book book) {
        Map<String, Rating> ratings = book.getRatings();

        if (ratings.isEmpty()) return 0.0;

        int totalRating = 0;

        for (Rating rating : ratings.values())
            totalRating += rating.getValue();

        return (double) totalRating / ratings.size();
    }

}



package com.geektext.backend.controllers;


import com.geektext.backend.dao.Book;
import com.geektext.backend.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller for returning a view of Books as JSON objects in a JSON array
 *
 * @author Michael Waller
 * @version 1.0.0
 */

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookRepository books;

    /**
     * Default controller for the BookController class
     *
     * @param books - book repository via spring bean dependency injection
     */
    @Autowired
    public BookController(BookRepository books) {
        this.books = books;
    }

    /**
     * This view displays all books
     */
    @GetMapping()
    public List<Book> books() {
        return books.findAll();
    }

    /**
     * This view displays all books by genre
     *
     * @param genre - request parameter for displaying books by genre
     */
    @GetMapping(params = "genre")
    public List<Book> viewByGenre(String genre) {
        return books.findBooksByGenreIgnoreCase(genre);
    }

    /**
     * This view displays the top 10 sold books
     */
    @GetMapping(path = "findbytopsellers")
    public List<Book> findByTopSeller() {
        return books.findTop10Sellers();
    }

    /**
     * This view displays books of N rating or higher
     *
     * @param ratings - request parameter for returning ratings
     */
    @GetMapping(params = "ratings")
    public List<Book> findByRatings(String ratings) {
        double numericValue;
        try { // tries to parse the string into a double datatype
            numericValue = Double.parseDouble(ratings);
        } catch (NumberFormatException numberFormatException) {
            return List.of(); // parsing is possible then a empty list is returned
        }

        return books.findBooksByRatingGreaterThanEqual(numericValue);
    }

    /**
     * This view discounts all books by a publisher by N percentage
     *
     * @param percent   - amount to discount the book by
     * @param publisher - name of the publisher to discount books
     */
    @ResponseBody
    @PutMapping(path = "updatebypublisher", params = {"Discount percent", "Publisher"})
    public String updateByPublisher(@RequestParam("Discount percent") String percent
            , @RequestParam("Publisher") String publisher) {

        //retrieves a list of books by publisher
        List<Book> updatable = books.findAllByPublisherIgnoreCase(publisher);

        if (updatable.isEmpty()) return "No publisher exist";

        double numericValue;

        try { // tries to parse the string into a double datatype
            numericValue = Double.parseDouble(percent);

        } catch (NumberFormatException numberFormatException) {
            // returns a number format exception if parsing is not possible
            return numberFormatException.getMessage();
        }

        //numeric value must not be below 0 or exceed 100
        if (numericValue < 0 || numericValue > 100) return "Numerical Value is out of range";

        numericValue /= 100;

        for (Book book : updatable) {
            double currentCost = book.getCost();
            if (currentCost <= 0) continue; // will not update books below or at the cost of 0
            double reduction = currentCost - (currentCost * numericValue);
            // Sets book to the new price. If price is below 0 then the book will be free.
            book.setCost(reduction >= 0 ? reduction : 0);
        }

        books.saveAll(updatable);

        return "success";
    }
}

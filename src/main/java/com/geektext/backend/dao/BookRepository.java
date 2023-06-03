package com.geektext.backend.dao;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A class dedicated to performing queries against a Book collection
 *
 * @author Michael Waller
 * @version 1.0.0
 * @see org.springframework.data.mongodb.repository.MongoRepository
 */
@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    /**
     * Returns a list of all books with the given name
     * @param name - name of the book(s)
     */
    List<Book> findAllByNameIgnoreCaseOrderBySoldDesc(String name);

    /**
     * Returns a list of books with the same genre
     * @param genre - name of the genre
     */
    List<Book> findBooksByGenreIgnoreCase(@Param("genre") String genre);


    /**
     * Returns a list of the 10 best-selling books in descending order
     */
    @Aggregation(pipeline = {
            "{ '$sort' : { 'sold' : -1 } }",
            "{ '$limit': 10}"
    })
    List<Book> findTop10Sellers();

    /**
     * Returns a list of books with a rating of N or better
     * @param rating - takes in the rating to compare by
     */
    List<Book> findBooksByRatingGreaterThanEqual(double rating);


    /**
     * Returns a list of books by a publisher
     * @param name - takes in the name of the publisher
     */
    List<Book> findAllByPublisherIgnoreCase(String name);

}

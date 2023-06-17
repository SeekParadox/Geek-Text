package com.geektext.backend.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class is an Object Relation Mapping for a MongoDB document
 * Represents a book entity.
 */
@Document("books")
@Data
public class Book {
    @Id
    private final String isbn;
    private final String name;
    private final String author;
    private final String description;
    private final String genre;
    private double cost;
    @JsonIgnore
    private final double rating;
    @JsonIgnore
    private final int sold;
    @JsonIgnore
    private final String publisher;
    private final int yearPublished;

    /**
     * Default constructor for mapping MongoDB objects.
     *
     * @param name          - takes in the name of the book
     * @param author        - takes in the author's name of the book
     * @param isbn          - takes in the 9 letter ISBN of the book
     * @param description   - takes in the book's description
     * @param genre         - takes in the genre of the book
     * @param cost          - takes in the cost of the book
     * @param rating        - takes in the rating of the book
     * @param sold          - takes in the amount of copies sold
     * @param publisher     - takes in the name of the publisher for this book
     * @param yearPublished - takes in the year that book was published
     */
    public Book(String name, String author, String isbn, String description, String genre, Double cost, Double rating, Integer sold, String publisher, Integer yearPublished) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
        this.genre = genre;
        this.cost = cost == null ? 0 : cost;
        this.rating = rating == null ? 0 : rating;
        this.sold = sold == null ? 0 : sold;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
    }
}

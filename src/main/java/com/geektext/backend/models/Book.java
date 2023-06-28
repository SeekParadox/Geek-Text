package com.geektext.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class is an Object Relation Mapping for a MongoDB documents
 *
 * @author Michael Waller
 * @version 1.0.0
 */

@Document("books")
public class Book {
    @Id
    private String id;
    private final String name;
    private final String author;
    @JsonIgnore
    private final String isbn;
    private final String description;
    private final String genre;
    private double cost;
    @JsonIgnore
    private final double rating;
    @JsonIgnore
    private final int sold;
    @JsonIgnore
    private final String publisher;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public double getRatings() {
        return rating;
    }

    public int getSold() {
        return sold;
    }

    public double getCost() {
        return cost;
    }

    public double getRating() {
        return rating;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Default constructor for mapping MongoDB objects
     *
     * @param id          - takes in unique id number of the Book
     * @param name        - takes in the name of the book
     * @param author      - takes in the author's name of the book
     * @param isbn        - takes in the 9 letter ISBN of the book
     * @param description - takes in the books description
     * @param genre       - takes in the genre of the book
     * @param cost        - takes in the cost of the book
     * @param rating      - takes in the rating of the book
     * @param sold        - takes in the amount of copies sold
     * @param publisher   - takes in the name of the publisher for this book
     */
    public Book(String id, String name, String author, String isbn
            , String description, String genre, Double cost, Double rating, Integer sold, String publisher) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
        this.genre = genre;
        this.cost = cost == null ? 0 : cost;
        this.rating = rating == null ? 0 : rating;
        this.sold = sold == null ? 0 : sold;
        this.publisher = publisher;
    }
}

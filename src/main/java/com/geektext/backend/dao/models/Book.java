package com.geektext.backend.dao.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * This class is an Object Relation Mapping for a MongoDB document
 * Represents a book entity.
 */
@Document("books")
@Data
public class Book {
    @Pattern(regexp = "\\d{13}", message = "ISBN must be exactly 13 digits")
    @NotEmpty(message = "ISBN must not be empty")
    @Id
    @Indexed(unique = true)
    private final String isbn;

    @Size(max = 50)
    @NotEmpty(message = "Name must not be empty")
    private final String name;

    @NotEmpty(message = "Author ID must not be empty")
    private final String authorId;

    @NotEmpty(message = "Description must not be empty")
    private final String description;

    @Size(max = 50)
    @NotEmpty(message = "Genre must not be empty")
    private final String genre;

    @PositiveOrZero
    private double cost;

    @DecimalMin(value = "0.0", inclusive = false)
    private final double rating;

    @NotNull(message = "field should not be null")
    @Min(0)
    private final Integer sold;

    @NotEmpty
    private final String publisher;

    @NotNull
    @Min(1900)
    private final Integer yearPublished;



    /**
     * Default constructor for mapping MongoDB objects.
     *
     * @param name          - takes in the name of the book
     * @param authorId      - takes in the authors ID
     * @param isbn          - takes in the 9 letter ISBN of the book
     * @param description   - takes in the book's description
     * @param genre         - takes in the genre of the book
     * @param cost          - takes in the cost of the book
     * @param rating        - takes in the rating of the book
     * @param sold          - takes in the amount of copies sold
     * @param publisher     - takes in the name of the publisher for this book
     * @param yearPublished - takes in the year that book was published
     */
    public Book(String name, String authorId, String isbn, String description, String genre, double cost, Double rating, Integer sold, String publisher, Integer yearPublished) {
        this.name = name;
        this.authorId = authorId;
        this.isbn = isbn;
        this.description = description;
        this.genre = genre;
        this.cost = cost;
        this.rating = rating == null ? 0 : rating;
        this.sold = sold;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
    }
}

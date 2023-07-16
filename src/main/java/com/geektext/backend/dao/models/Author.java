package com.geektext.backend.dao.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "authors")
public class Author {
    @Id
    @NotEmpty(message = "Author ID must not be empty")
    private String authorId;

    @Size(max = 50)
    @NotEmpty(message = "First Name must not be empty")
    private String firstName;

    @Size(max = 50)
    @NotEmpty(message = "Last Name must not be empty")
    private String lastName;

    @NotEmpty(message = "Biography must not be empty")
    private String biography;

    @Size(max = 50)
    @NotEmpty(message = "Publisher must not be empty")
    private String publisher;

    @DBRef
    private List<Book> books;
}

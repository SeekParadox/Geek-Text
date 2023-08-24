package com.geektext.backend.controllers;

import com.geektext.backend.models.Author;
import com.geektext.backend.models.Book;
import com.geektext.backend.dao.repository.AuthorRepository;
import com.geektext.backend.dao.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Default controller for the AuthorController class
 *
 * @param /authors - book repository via spring bean dependency injection
 */
@RestController
@RequestMapping("api/author")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    /**
     * Api route that creates an author with json details
     */
    @PostMapping
    public ResponseEntity<?> createAuthor(@Valid @RequestBody Author author, BindingResult bindingResult) {
        // Checks for validation errors and binds it to a string to return as an error message.
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        // Creates Author if there are no validation errors.
        Author createdAuthor = authorRepository.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);

    }

    /**
     * Get request that displays all authors
     */
    @GetMapping
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    /**
     * Get request that gets books by a certain author using an author id
     */
    @GetMapping("{authorId}/books")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable("authorId") String authorId) {
        // Creates a list of books containing books by an author
        List<Book> books = bookRepository.findByAuthorId(authorId);
        // Checks if the list contains books and if not returns an empty list
        if (!books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

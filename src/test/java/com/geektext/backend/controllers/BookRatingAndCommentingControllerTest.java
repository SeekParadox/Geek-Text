package com.geektext.backend.controllers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import com.geektext.backend.models.Book;
import com.geektext.backend.models.Comment;
import com.geektext.backend.models.Rating;
import com.geektext.backend.service.BookService;


import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest
@AutoConfigureMockMvc
class BookRatingAndCommentingControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    void testCreateRating() throws Exception {
        // Mock data
        String bookId = "1";
        int ratingValue = 4;
        String userId = "user1";


        // Create a mock Book object
          // Create a mock Book object
    Book book = new Book(bookId, "Book 1", "Author 1", "ISBN-123456789",
        "Description 1", "Genre 1", 0.0, 0.0, 0, "Publisher 1");
         // Set up a mock BookService
    BookService bookService = mock(BookService.class);
    when(bookService.findBookById(bookId)).thenReturn(book);


    // Create the controller with the mock BookService
    BookRatingAndCommentingController controller = new BookRatingAndCommentingController(bookService);


    // Perform rating creation
    ResponseEntity<String> response = controller.createRating(bookId, ratingValue, userId);


    // Verify the response
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Rating added successfully", response.getBody());


    // Verify that the bookService method was called
    verify(bookService, times(1)).findBookById(bookId);
    verify(bookService, times(1)).saveBook(book);


    // Verify that the rating is added to the book's ratings map
    Map<String, Rating> ratings = book.getRatings();
    assertTrue(ratings.containsKey(userId));
    Rating addedRating = ratings.get(userId);
    assertEquals(ratingValue, addedRating.getValue());
}

    @Test
    void testCreateComment() throws Exception {
      // Mock data
        String bookId = "1";
        String comment = "Great book!";
        String userId = "user1";


         Book book = new Book(bookId, "Book 1", "Author 1", "ISBN-123456789",
        "Description 1", "Genre 1", 0.0, 0.0, 0, "Publisher 1");


            // Set up a mock BookService
        BookService bookService = mock(BookService.class);
        when(bookService.findBookById(bookId)).thenReturn(book);


        // Create the controller with the mock BookService
        BookRatingAndCommentingController controller = new BookRatingAndCommentingController(bookService);


        // Perform comment creation
        ResponseEntity<String> response = controller.createComment(bookId, comment, userId);


        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Comment added successfully", response.getBody());


        // Verify that the bookService method was called
        verify(bookService, times(1)).findBookById(bookId);
        verify(bookService, times(1)).saveBook(book);


        // Verify that the comment is added to the book's comments map
        Map<String, Comment> comments = book.getComments();
        assertTrue(comments.containsKey(userId));
        Comment addedComment = comments.get(userId);
        assertEquals(comment, addedComment.getComment());
    }


        @Test
    void testGetBookComments() throws Exception {
        // Mock data
        String bookId = "1";


        Book book = new Book(bookId, "Book 1", "Author 1", "ISBN-123456789",
        "Description 1", "Genre 1", 0.0, 0.0, 0, "Publisher 1");


        // Create a list of comments
        List<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment("Great book!", new Date());
        Comment comment2 = new Comment("Nice read!", new Date());
        comments.add(comment1);
        comments.add(comment2);


        // Add comments to the book
        book.getComments().put("user1", comment1);
        book.getComments().put("user2", comment2);


        // Set up a mock BookService
        BookService bookService = mock(BookService.class);
        when(bookService.findBookById(bookId)).thenReturn(book);


        // Create the controller with the mock BookService
        BookRatingAndCommentingController controller = new BookRatingAndCommentingController(bookService);


        // Perform comment retrieval
        ResponseEntity<List<Comment>> response = controller.getBookComments(bookId);


        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comments, response.getBody());


        // Verify that the bookService method was called
        verify(bookService, times(1)).findBookById(bookId);


        // Verify the size and contents of the returned comments list
        List<Comment> returnedComments = response.getBody();
        assertNotNull(returnedComments);
        assertEquals(2, returnedComments.size());
        assertTrue(returnedComments.contains(comment1));
        assertTrue(returnedComments.contains(comment2));
    }

    @Test
    void testGetBookAverageRating() throws Exception {
        // Mock data
        String bookId = "1";


        Book book = new Book(bookId, "Book 1", "Author 1", "ISBN-123456789",
        "Description 1", "Genre 1", 0.0, 0.0, 0, "Publisher 1");


        // Create a list of ratings
        List<Rating> ratings = new ArrayList<>();
        Rating rating1 = new Rating(4, new Date());
        Rating rating2 = new Rating(5, new Date());
        ratings.add(rating1);
        ratings.add(rating2);


         // Add comments to the book
        book.getRatings().put("user1", rating1);
        book.getRatings().put("user2", rating2);


        // Set up a mock BookService
        BookService bookService = mock(BookService.class);
        when(bookService.findBookById(bookId)).thenReturn(book);


        // Create the controller with the mock BookService
        BookRatingAndCommentingController controller = new BookRatingAndCommentingController(bookService);


        // Perform average rating retrieval
        ResponseEntity<Double> averageRatingResponse = controller.getBookAverageRating(bookId);


        // Calculate the expected average rating
        double expectedAverageRating = (rating1.getValue() + rating2.getValue()) / 2.0;


        // Verify the average rating response
        assertEquals(HttpStatus.OK, averageRatingResponse.getStatusCode());
        assertEquals(expectedAverageRating, averageRatingResponse.getBody(), 0.01);


        // Verify that the bookService method was called
        verify(bookService, times(1)).findBookById(bookId);
    }


}



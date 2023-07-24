package com.geektext.backend.controllers;

import com.geektext.backend.dao.Book;
import com.geektext.backend.dao.BookRepository;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void books() throws Exception {
        mockMvc.perform(get("/api/books")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void viewByGenre() throws Exception {
        mockMvc
                .perform(get("/api/books")
                        .param("genre", "Fantasy")
                )
                .andDo(print())
                .andExpect(
                        jsonPath(
                                "$.[*].genre"
                                , everyItem(is("Fantasy"))
                        )
                );

        mockMvc
                .perform(
                        get("/api/books").param("genre", "Mystery")
                )
                .andDo(print())
                .andExpect(
                        jsonPath(
                                "$.[*].genre"
                                , everyItem(is("Mystery"))
                        ));
    }

    @Test
    void findByTopSeller() throws Exception {
        mockMvc
                .perform(get("/api/books/findbytopsellers"))
                .andDo(print())
                .andExpect(jsonPath("$.[*].name", customMatcher()));
    }

    @Test
    void findByRatings() throws Exception {
        mockMvc
                .perform(
                        get("/api/books")
                                .param("ratings", "3")
                )
                .andExpect(
                        jsonPath(
                                "$.[*].ratings"
                                , everyItem(is(greaterThanOrEqualTo(3.00))
                                )
                        )
                );

        mockMvc
                .perform(
                        get("/api/books")
                                .param("ratings", "4")
                )
                .andExpect(
                        jsonPath(
                                "$.[*].ratings"
                                , everyItem(is(greaterThanOrEqualTo(4.00))
                                )
                        )
                );
    }

    @Test
    void updateByPublisher() {
    }

    @Autowired
    BookRepository repository;

    public Matcher<Iterable<Object>> customMatcher() {
        return new TypeSafeMatcher<>() {

            @Override
            protected boolean matchesSafely(Iterable<Object> items) {
                PriorityQueue<Book> testQueue = new PriorityQueue<>(Comparator.comparingInt(Book::getSold));
                for (Object item : items) {
                    Book book = repository.findAllByNameIgnoreCaseOrderBySoldDesc(item.toString()).get(0);
                    testQueue.add(book);
                }

                for (Object item : items) {
                    if ((item.toString().equals(Objects.requireNonNull(testQueue.poll()).getName()))) return false;
                }
                return true;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("The ");
            }
        };
    }
}
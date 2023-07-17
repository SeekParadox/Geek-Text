package com.geektext.backend.controllers;


import com.geektext.backend.dao.Book;
import com.geektext.backend.dao.BookRepository;
import com.geektext.backend.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * Controller class for managing the shopping cart.
 */
@Controller
public class ShoppingCartController {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);
    private final ShoppingCartService shoppingCartService;
    private final BookRepository bookRepository;

    /**
     * Default controller for the ShoppingCartController class
     *
     * @param shoppingCartService autowired ShoppingCartService
     * @param bookRepository      autowired BookRepository
     */
    public ShoppingCartController(ShoppingCartService shoppingCartService, BookRepository bookRepository) {
        this.shoppingCartService = shoppingCartService;
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieves the shopping cart view.
     *
     * @param model the model for the view.
     * @return cart view template.
     */
    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("books", shoppingCartService.getCart());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());
        return "cart";
    }

    /**
     * Adds a book to the shopping cart.
     *
     * @param id the ID of the book to be added.
     * @return a redirect to the home page.
     */
    @GetMapping("/cart/add/{id}")
    public String addBookToCart(@PathVariable("id") String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            shoppingCartService.addBook(id);
            logger.debug(String.format("Book with id: %s added to shopping cart.", id));
        }
        return "redirect:/home";
    }

    /**
     * Removes a book from the shopping cart.
     *
     * @param id the ID of the book to be removed.
     * @return a redirect to the cart page.
     */
    @GetMapping("/cart/remove/{id}")
    public String removeBookFromCart(@PathVariable("id") String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            shoppingCartService.removeBook(id);
            logger.debug(String.format("Book with id: %s removed from shopping cart.", id));
        } else {

        }
        return "redirect:/cart";
    }

    /**
     * Clears all books in the shopping cart.
     *
     * @return a redirect to the cart page.
     */
    @GetMapping("/cart/clear")
    public String clearBooksInCart() {
        shoppingCartService.clear();
        return "redirect:/cart";
    }

    /**
     * Performs the checkout process.
     *
     * @param model the model for the view.
     * @return a redirect to the cart page.
     */
    @GetMapping("/cart/checkout")
    public String cartCheckout(Model model) {
        //checkout
        shoppingCartService.checkout();
        return "redirect:/cart";
    }
}

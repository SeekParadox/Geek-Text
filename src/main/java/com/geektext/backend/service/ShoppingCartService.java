package com.geektext.backend.service;

import com.geektext.backend.dao.repository.BookRepository;
import com.geektext.backend.models.Book;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class representing a shopping cart.
 * An instance of this class is created for each session, thus providing a shopping cart for each user.
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartService implements IShoppingCartService {
    private final Map<String, Integer> shoppingCart = new LinkedHashMap<>();
    private final BookRepository bookRepository;

    /**
     * Default ShoppingCartService constructor
     *
     * @param bookRepository - autowired BookRepository.
     */
    public ShoppingCartService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Adds a book to the shopping cart.
     *
     * @param id the ID of the book to be added.
     */
    @Override
    public void addBook(String id) {

        if (shoppingCart.containsKey(id)) {
            shoppingCart.put(id, shoppingCart.get(id) + 1);
        } else {
            shoppingCart.put(id, 1);
        }

    }

    /**
     * Removes a book from the shopping cart.
     *
     * @param id the ID of the book to be removed.
     */
    @Override
    public void removeBook(String id) {
        if (shoppingCart.containsKey(id)) {
            if (shoppingCart.get(id) > 1)
                shoppingCart.replace(id, shoppingCart.get(id) - 1);
            else if (shoppingCart.get(id) == 1) {
                shoppingCart.remove(id);
            }
        }
    }

    /**
     * Clears the shopping cart.
     */
    @Override
    public void clear() {
        shoppingCart.clear();
    }

    /**
     * Retrieves the contents of the shopping cart.
     *
     * @return an unmodifiable map of books and their quantities in the cart.
     */
    @Override
    public Map<Book, Integer> getCart() {
        return Collections.unmodifiableMap(shoppingCart.entrySet().stream().collect(
                Collectors.toMap(e -> bookRepository.findById(e.getKey()).get(), e -> e.getValue())));
    }

    /**
     * Calculates the total price of the books in the shopping cart.
     *
     * @return the total price of the books.
     */
    @Override
    public double totalPrice() {
        return shoppingCart.entrySet().stream()
                .map(k -> bookRepository.findById(k.getKey()).get().getCost() * k.getValue())
                .reduce(Double::sum)
                .orElse(0D);
    }

    /**
     * Performs the checkout process.
     */
    @Override
    public void checkout() {
        //checkout logic, if needed
    }

}

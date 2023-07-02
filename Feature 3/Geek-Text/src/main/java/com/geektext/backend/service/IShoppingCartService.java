package com.geektext.backend.service;

import com.geektext.backend.dao.Book;

import java.util.Map;

public interface IShoppingCartService {
    void addBook(String id);

    void removeBook(String id);

    void clear();

    Map<Book, Integer> getCart();

    double totalPrice();

    void checkout();
}

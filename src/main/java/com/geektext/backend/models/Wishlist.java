package com.geektext.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "wishlists")
public class Wishlist {

    @Id
    private String id;  

    @Column
    private String user; //User
    @Column
    private String title; //Title of wishlist
    @Column
    private ArrayList<String> books;  //List is ISBN's


    public Wishlist(String id, String user, String title) {
        this.id = id;
        this.user = user;
        this.title = title;
    }
    public Wishlist(){
        this.books = new ArrayList<String>();
    }

    
   //Get ID
    public String getId() {
        return id;
    }
    //Set ID
    public void setId(String id) {
        this.id = id;
    }

    //Get User
    public String getUser() {
        return user;
    }

    //Set User
    public void setUser(String user) {
        this.user = user;
    }

    //Get Title
    public String getTitle() {
        return title;
    }

    //Set Title
    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getBooks() {
        return this.books;
    }
    public void addBook(String newBook) {
        this.books.add(newBook);
    }

    //While remove would return successfully
    public void removeBook(String book) {
        while(this.books.remove(book)) {} 
    }


}
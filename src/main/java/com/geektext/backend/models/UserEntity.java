package com.geektext.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

@Document(collection = "users")
public class UserEntity {
    @Id private String username;
    private String password;

    private String name;

    private String email;

    private List<String> address;

    private List<String> homeAddress;
    private CreditCard creditCard;

    public UserEntity() {

    }

    public UserEntity(String username, String password, boolean enabled, List<String> userRoles, CreditCard creditCard) {
        this.username = username;
        this.password = password;
        this.creditCard = creditCard;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public List<String> getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(List<String> homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
package com.geektext.backend;

import java.util.List;

public class UserDataParser {

    private String username;
    private String password;
    private String name;
    private String email;
    private List<String> address;
    private List<String> homeaddress;

//    public MyJsonParser() {
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<String> getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(List<String> homeaddress) {
        this.homeaddress = homeaddress;
    }
}
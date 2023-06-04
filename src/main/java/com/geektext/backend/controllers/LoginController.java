package com.geektext.backend.controllers;

import com.geektext.backend.MyJsonParser;
import com.geektext.backend.security.CreateUserService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * This class is responsible for routes to the login page and registration page
 *
 * @author Michael Waller
 */
@Controller
public class LoginController {
    private final CreateUserService userService;

    /**
     * This is the default constructor
     *
     * @param userService - takes in the class to create a user
     */
    @Autowired
    public LoginController(CreateUserService userService) {
        this.userService = userService;
    }

    /**
     * THis method is for the login view
     *
     * @return - login.html
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * This method is for the logout view
     *
     * @return - logout.html
     */
    @GetMapping("logout")
    public String logout() {
        return "logout";
    }

    /**
     * This method is for registering new users
     *
     * @param jsonUser - Json Object with user information
     * @return - user creation status
     */
    @ResponseBody
    @PostMapping("/register")
    public String registration(@RequestBody MyJsonParser jsonUser) {
        if (jsonUser.getUsername() == null || jsonUser.getPassword() == null)
            return "failed: Did not provide required fields";

        try {
            userService.createUser(jsonUser);
        } catch (Exception e) {
            return "failed: " + e.getMessage();
        }

        return "success";
    }
}
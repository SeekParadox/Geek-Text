package com.geektext.backend.controllers;

import com.geektext.backend.models.CreditCard;
import com.geektext.backend.dao.UserService;
import com.geektext.backend.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping("/user")
    public ResponseEntity<UserEntity> getUser(@RequestBody UserEntity jsonUser) {
        String username = jsonUser.getUsername();
        if (username.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if (!userService.doesUserExist(jsonUser.getUsername())) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        UserEntity user = userService.getUser(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody UserEntity jsonUser) {
        if (jsonUser.getUsername() == null || jsonUser.getPassword() == null)
            return new ResponseEntity<>("Username or Password were not passed into body", HttpStatus.BAD_REQUEST);

        if (userService.doesUserExist(jsonUser.getUsername())) {
            return new ResponseEntity<>("User (unique by username) already exists", HttpStatus.BAD_REQUEST);
        }

        try {
            userService.createUser(jsonUser);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/user")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity jsonUser) {
        if (!userService.doesUserExist(jsonUser.getUsername())) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        try {
            UserEntity updatedUser = userService.updateUser(jsonUser.getUsername(), jsonUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PostMapping("/creditCard")
    public ResponseEntity<String> createCreditCard(@RequestBody CreditCard jsonCreditCard) {
        if (!CreditCard.isValid(jsonCreditCard)) {
            return new ResponseEntity<>("You must fill out each field of the credit card object", HttpStatus.BAD_REQUEST);
        }

        if (!userService.doesUserExist(jsonCreditCard.getUsername())) {
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }

        userService.addCreditCard(jsonCreditCard.getUsername(), jsonCreditCard);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
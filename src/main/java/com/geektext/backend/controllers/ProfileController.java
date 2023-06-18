package com.geektext.backend.controllers;

import com.geektext.backend.UserDataParser;
import com.geektext.backend.dao.UserService;;
import com.geektext.backend.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping("/user")
    public ResponseEntity<UserEntity> getUser(@RequestBody UserDataParser jsonUser) {
        String username = jsonUser.getUsername();
        if (username.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if (!userService.findUserByUsername(jsonUser.getUsername())) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        UserEntity user = userService.findAllUsers(username).get(0);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody UserDataParser jsonUser) {
        if (jsonUser.getUsername() == null || jsonUser.getPassword() == null)
            return new ResponseEntity<>("Username or Password were not passed into body", HttpStatus.BAD_REQUEST);

        if (userService.findUserByUsername(jsonUser.getUsername())) {
            return new ResponseEntity<>("User (unique by username) already exists", HttpStatus.BAD_REQUEST);
        }

        try {
            userService.createUser(jsonUser);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
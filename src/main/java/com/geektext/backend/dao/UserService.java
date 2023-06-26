package com.geektext.backend.dao;


import com.geektext.backend.UserDataParser;
import com.geektext.backend.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository)  {
        this.repository = repository;
    }
    public boolean findUserByUsername(String username) {
        return repository.existsByUsernameIgnoreCase(username);
    }

    public void createUser(UserDataParser userData) throws Exception {
        if (findUserByUsername(userData.getUsername()))
            throw new Exception("Username Taken");

        UserEntity user = new UserEntity(userData);
        repository.save(user);
    }

    public List<UserEntity> findAllUsers(String username) {
        return repository.findAllByUsername(username);
    }
}
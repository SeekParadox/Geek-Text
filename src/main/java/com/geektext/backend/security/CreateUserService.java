package com.geektext.backend.security;


import com.geektext.backend.dao.UserRepository;
import com.geektext.backend.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    private final UserRepository repository;

    @Autowired
    public CreateUserService(UserRepository repository)  {
        this.repository = repository;
    }
    private boolean findUserByUsername(String username) {
        return repository.existsByUsernameIgnoreCase(username);
    }

    public void createUser(UserEntity object) throws Exception {
        if (findUserByUsername(object.getUsername()))
            throw new Exception("Username Taken");

        repository.save(object);
    }
}

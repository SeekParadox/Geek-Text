package com.geektext.backend.dao;


import com.geektext.backend.models.CreditCard;
import com.geektext.backend.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository)  {
        this.repository = repository;
    }

    public boolean doesUserExist(String username) {
        return repository.existsByUsernameIgnoreCase(username);
    }

    public void createUser(UserEntity user) throws Exception {
        if (repository.existsByUsernameIgnoreCase(user.getUsername()))
            throw new Exception("Username Taken");

        repository.save(user);
    }

    public UserEntity getUser(String username) {
        return repository.findByUsername(username);
    }

    public UserEntity updateUser(String username, UserEntity userEntity) {
        UserEntity user = repository.findByUsername(username);

        if (userEntity.getName() == null || !userEntity.getName().isEmpty()) {
            user.setName(userEntity.getName());
        }

        if (userEntity.getPassword() == null || !userEntity.getPassword().isEmpty()) {
            user.setPassword(userEntity.getPassword());
        }

        if (userEntity.getAddress() == null || !userEntity.getAddress().isEmpty()) {
            user.setAddress(userEntity.getAddress());
        }

        if (userEntity.getHomeAddress() == null || !userEntity.getHomeAddress().isEmpty()) {
            user.setHomeAddress(userEntity.getHomeAddress());
        }

        return repository.save(user);
    }

    public void addCreditCard(String username, CreditCard creditCard) {
        UserEntity user = repository.findByUsername(username);
        user.setCreditCard(creditCard);
        repository.save(user);
    }
}
package com.geektext.backend.security;


import com.geektext.backend.MyJsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public void createUser(MyJsonParser object) throws Exception {
        if (findUserByUsername(object.getUsername()))
            throw new Exception("Username Taken");

        UserEntity user = new UserEntity();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
        String encodedPassword = passwordEncoder.encode(object.getPassword());

        user.setUsername(object.getUsername());
        user.setPassword(encodedPassword);
        user.setName(object.getName());
        user.setEmail(object.getEmail());
        user.setAddress(object.getAddress());
        user.setHomeAddress(object.getHomeaddress());
        user.setEnabled(true);
        user.setUserRoles(List.of("user"));

        repository.save(user);
    }
}

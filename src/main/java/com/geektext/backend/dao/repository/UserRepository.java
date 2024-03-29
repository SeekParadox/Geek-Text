package com.geektext.backend.dao.repository;

import com.geektext.backend.models.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    List<UserEntity> findAllByUsername(String username);

    boolean existsByUsernameIgnoreCase(String username);

    UserEntity findByUsername(String username);


}

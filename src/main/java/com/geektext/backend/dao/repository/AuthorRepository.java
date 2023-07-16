package com.geektext.backend.dao.repository;

import com.geektext.backend.dao.models.Author;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
    @Override
    @Cacheable("authors")
    List<Author> findAll();

}

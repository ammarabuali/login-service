package org.example.loginapi.repo;


import org.example.loginapi.model.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepo extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
}



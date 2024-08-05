package org.example.loginapi.repo;


import org.example.loginapi.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UsersRepo extends MongoRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
}



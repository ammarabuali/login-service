package org.example.loginapi.service;

import org.example.loginapi.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> findAll();
    UserEntity findByUsername(String username);
    UserEntity createUser(UserEntity user);
    void deleteById(String id);
}

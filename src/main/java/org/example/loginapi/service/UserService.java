package org.example.loginapi.service;

import org.example.loginapi.entity.UserEntity;
import org.example.loginapi.entity.dto.UserDto;
import org.example.loginapi.entity.response.DeleteResponse;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findByUsername(String username);

    UserDto createUser(UserEntity user);

    DeleteResponse deleteById(String id);

    DeleteResponse deleteAll();
}

package org.example.loginapi.service;

import lombok.RequiredArgsConstructor;
import org.example.loginapi.entity.UserEntity;
import org.example.loginapi.entity.dto.UserDto;
import org.example.loginapi.entity.response.DeleteResponse;
import org.example.loginapi.exception.UserAlreadyExistsException;
import org.example.loginapi.exception.UserServiceException;
import org.example.loginapi.mapper.UserMapper;
import org.example.loginapi.repo.UserRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findByUsername(String username) {
        try {
            UserEntity user = userRepository.findByUsername(username).orElseThrow();
            return UserMapper.INSTANCE.toDto(user);
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("user not found " + username + " " + e.getMessage());
        } catch (Exception e) {
            throw new UserServiceException("An error occurred while finding user by username: " + e.getMessage());
        }
    }

    @Override
    public UserDto createUser(UserEntity user) {
        try {
            validateUserNonExistence(user);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return UserMapper.INSTANCE.toDto(user);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists.");
        } catch (Exception e) {
            throw new UserServiceException("An error occurred while creating user " + " " + user.getUsername() + " " + e.getMessage());
        }
    }


    @Override
    public DeleteResponse deleteById(String id) {
        try {
            userRepository.deleteById(id);
            return DeleteResponse.builder().deleted(true).build();
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User with ID " + id + " does not exist.");
        }
    }

    @Override
    public DeleteResponse deleteAll() {
        userRepository.deleteAll();
        return DeleteResponse.builder().deleted(true).build();
    }


    private void validateUserNonExistence(UserEntity user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()
                || userRepository.findByEmail(user.getEmail()).isPresent()
                || userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
    }

}


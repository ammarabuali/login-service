package org.example.loginapi.service;

import lombok.RequiredArgsConstructor;
import org.example.loginapi.entity.UserEntity;
import org.example.loginapi.exception.UserAlreadyExistsException;
import org.example.loginapi.exception.UserServiceException;
import org.example.loginapi.repo.UsersRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepo userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findByUsername(String username) {
        try {
            UserEntity user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username " + username + " not found.");
            }
            return user;
        } catch (Exception e) {
            throw new UserServiceException("An error occurred while finding user by username: " + e.getMessage());
        }
    }


    public UserEntity createUser(UserEntity user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists.");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public void deleteById(String id) {
        if (!userRepository.existsById(id)) {
            throw new UsernameNotFoundException("User with ID " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }



}

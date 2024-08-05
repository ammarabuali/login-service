package org.example.loginapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.loginapi.entity.UserEntity;
import org.example.loginapi.exception.UserAlreadyExistsException;
import org.example.loginapi.exception.UserServiceException;
import org.example.loginapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/user")
    public UserEntity getUserByUsername(@RequestParam String username) {
        return userService.findByUsername(username);
    }


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserEntity user) {
        try {
            UserEntity createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new UserServiceException("An unexpected error occurred while creating user: " + ex.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>("User with ID " + id + " deleted successfully.", HttpStatus.NO_CONTENT);
        } catch (UsernameNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new UserServiceException("An unexpected error occurred while deleting user with ID " + id);
        }
    }

}


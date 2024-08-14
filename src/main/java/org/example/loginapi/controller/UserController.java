package org.example.loginapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.loginapi.entity.UserEntity;
import org.example.loginapi.entity.dto.UserDto;
import org.example.loginapi.entity.request.DeleteUserRequest;
import org.example.loginapi.entity.request.FindUserRequest;
import org.example.loginapi.entity.response.DeleteResponse;
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

    @GetMapping("/find/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/find")
    public ResponseEntity<UserDto> getUserByUsername(@RequestBody FindUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByUsername(request.getUsername()));
    }


    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserEntity user) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }


    @DeleteMapping("/delete")
    public ResponseEntity<DeleteResponse> deleteUser(@RequestBody DeleteUserRequest request) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteById(request.getId()));
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<DeleteResponse> deleteAllUsers() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.deleteAll());

    }

}


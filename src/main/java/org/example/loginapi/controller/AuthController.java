package org.example.loginapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.loginapi.entity.UserCredentials;
import org.example.loginapi.entity.response.AuthResponse;
import org.example.loginapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserCredentials userCredentials) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.authenticateUser(userCredentials));
    }
}

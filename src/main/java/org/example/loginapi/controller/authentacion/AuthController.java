package org.example.loginapi.controller.authentacion;

import jakarta.validation.Valid;
import org.example.loginapi.annotation.CommonResponse;
import org.example.loginapi.model.request.UserCredentials;
import org.example.loginapi.model.response.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface AuthController {
    @PostMapping("/login")
    @CommonResponse
    ResponseEntity<AuthResponse> login(@Valid UserCredentials userCredentials);
}

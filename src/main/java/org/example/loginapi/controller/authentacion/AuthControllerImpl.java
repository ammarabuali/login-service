package org.example.loginapi.controller.authentacion;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.loginapi.model.request.UserCredentials;
import org.example.loginapi.model.response.AuthResponse;
import org.example.loginapi.service.authentacion.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;


    @Override
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid UserCredentials userCredentials) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.authenticateUser(userCredentials));
    }
}


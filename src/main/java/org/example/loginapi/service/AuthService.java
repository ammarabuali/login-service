package org.example.loginapi.service;

import org.example.loginapi.entity.UserCredentials;
import org.example.loginapi.entity.response.AuthResponse;

public interface AuthService {
    AuthResponse authenticateUser(UserCredentials userCredentials);
}

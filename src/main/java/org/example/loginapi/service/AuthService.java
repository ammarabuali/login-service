package org.example.loginapi.service;

import org.example.loginapi.entity.UserCredentials;

public interface AuthService {
    String authenticateUser(UserCredentials userCredentials);
}

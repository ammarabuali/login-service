package org.example.loginapi.service;

public interface JwtService {
    String generateToken(String username);
}

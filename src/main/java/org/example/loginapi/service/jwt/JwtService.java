package org.example.loginapi.service.jwt;

import java.util.Map;

public interface JwtService {
    String generateToken(String email, Map<String, Object> claims);
}

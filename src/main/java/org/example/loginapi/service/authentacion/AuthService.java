package org.example.loginapi.service.authentacion;

import org.example.loginapi.model.request.UserCredentials;
import org.example.loginapi.model.response.AuthResponse;

public interface AuthService {
    AuthResponse authenticateUser(UserCredentials userCredentials);
}

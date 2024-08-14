package org.example.loginapi.service;

import lombok.RequiredArgsConstructor;
import org.example.loginapi.entity.UserCredentials;
import org.example.loginapi.entity.UserEntity;
import org.example.loginapi.entity.response.AuthResponse;
import org.example.loginapi.exception.InvalidPasswordException;
import org.example.loginapi.exception.TokenGenerationException;
import org.example.loginapi.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepo usersRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AuthResponse authenticateUser(UserCredentials userCredentials) {
        try {
            UserEntity user = usersRepo.findByUsername(userCredentials.getUsername())
                    .orElseThrow();
            if (!passwordEncoder.matches(userCredentials.getPassword(), user.getPassword())) {
                throw new InvalidPasswordException();
            }
            return AuthResponse.builder().token(jwtService.generateToken(userCredentials.getUsername())).build();
        } catch (InvalidPasswordException e) {
            throw new InvalidPasswordException("Wrong Credentials!, \nusername or password is incorrect " + e.getMessage());
        } catch (Exception e) {
            throw new TokenGenerationException("Something went wrong generating token " + e.getMessage());
        }
    }

}

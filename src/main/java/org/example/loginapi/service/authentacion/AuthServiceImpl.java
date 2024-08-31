package org.example.loginapi.service.authentacion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.loginapi.model.request.UserCredentials;
import org.example.loginapi.model.entity.UserEntity;
import org.example.loginapi.model.response.AuthResponse;
import org.example.loginapi.exception.InvalidPasswordException;
import org.example.loginapi.exception.TokenGenerationException;
import org.example.loginapi.repo.UserRepo;
import org.example.loginapi.service.jwt.JwtService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@Slf4j
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
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", user.getRole());
            claims.put("permissions", user.getPermissions());
            return AuthResponse.builder().token(jwtService.generateToken(userCredentials.getUsername(), claims)).build();
        } catch (NoSuchElementException e) {
            log.error("No such user exists with username {}", userCredentials.getUsername());
            throw new UsernameNotFoundException("No such user exists with username " + userCredentials.getUsername());
        } catch (InvalidPasswordException e) {
            throw new InvalidPasswordException("Wrong Credentials!, \nusername or password is incorrect " + e.getMessage());
        } catch (Exception e) {
            throw new TokenGenerationException("Something went wrong generating token " + e.getMessage());
        }
    }

}


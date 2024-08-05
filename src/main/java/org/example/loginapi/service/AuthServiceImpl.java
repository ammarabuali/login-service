package org.example.loginapi.service;

import lombok.RequiredArgsConstructor;
import org.example.loginapi.entity.UserCredentials;
import org.example.loginapi.entity.UserEntity;
import org.example.loginapi.exception.InvalidPasswordException;
import org.example.loginapi.repo.UsersRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UsersRepo usersRepo;
    private final JwtService jwtService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String authenticateUser(UserCredentials userCredentials) {
        UserEntity user = usersRepo.findByUsername(userCredentials.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (!passwordEncoder.matches(userCredentials.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
        return jwtService.generateToken(userCredentials.getUsername());
    }

}

package org.example.loginapi.service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.loginapi.exception.KeyGenerationException;
import org.example.loginapi.exception.TokenGenerationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getSigningKey() {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(secret);
            return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
        } catch (IllegalArgumentException e) {
            throw new KeyGenerationException("Invalid Base64 encoding for secret key.");
        } catch (Exception e) {
            throw new KeyGenerationException("Unexpected error occurred while generating signing key.");
        }
    }


    @Override
    public String generateToken(String email, Map<String, Object> additionalClaims) {
        try {
            // Build the JWT with additional claims
            return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration))
                    .addClaims(additionalClaims)
                    .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                    .compact();
        } catch (Exception e) {
            throw new TokenGenerationException("Could not generate JWT token");
        }
    }


}

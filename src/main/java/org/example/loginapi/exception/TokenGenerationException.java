package org.example.loginapi.exception;

public class TokenGenerationException extends RuntimeException {
    public TokenGenerationException(String message) {
        super(message);
    }
}

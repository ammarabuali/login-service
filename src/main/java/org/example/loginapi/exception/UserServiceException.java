package org.example.loginapi.exception;

public class UserServiceException extends RuntimeException {
    public UserServiceException(String message) {
        super(message);
    }
}

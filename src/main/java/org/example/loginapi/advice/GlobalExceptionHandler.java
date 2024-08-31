package org.example.loginapi.advice;

import org.example.loginapi.model.dto.ErrorResponseDTO;
import org.example.loginapi.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUsernameNotFoundException() {
        Map<String, String> errors = new HashMap<>();
        errors.put("username", "Username not found in the system.");
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .message("Username Not Found!")
                        .status(HttpStatus.NOT_FOUND).errors(errors)
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidPassword() {
        Map<String, String> errors = new HashMap<>();
        errors.put("password", "Password is incorrect.");
        return new ResponseEntity<>(
                ErrorResponseDTO.builder().errors(errors).message("Invalid password!").status(HttpStatus.UNAUTHORIZED).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(KeyGenerationException.class)
    public ResponseEntity<ErrorResponseDTO> handleKeyGenerationException() {
        return new ResponseEntity<>(ErrorResponseDTO.builder().message("Unexpected error occurred while generating signing key.").status(HttpStatus.INTERNAL_SERVER_ERROR).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TokenGenerationException.class)
    public ResponseEntity<ErrorResponseDTO> handleTokenGenerationException() {
        return new ResponseEntity<>(ErrorResponseDTO.builder().message("An error occurred while generating token").status(HttpStatus.INTERNAL_SERVER_ERROR).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }




    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .message("An error occurred in bank account service")
                        .status(HttpStatus.BAD_REQUEST)
                        .errors(errors)
                        .build(), HttpStatus.BAD_REQUEST);
    }

}


package org.example.loginapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "USERS")
public class UserEntity {
    @Id
    private String id;

    @NotBlank(message = "Username can't be left empty")
    @Size(min = 4, max = 20)
    private String username;

    @NotBlank(message = "Phone number can't be left empty")
    @Pattern(regexp = "^\\+?[0-9 ()-]{7,15}$", message = "Invalid phone number")
    private String phoneNumber;


    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email can't be left empty")
    private String email;

    @NotBlank(message = "Password can't be left empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character")
    private String password;

    private List<String> roles;
}
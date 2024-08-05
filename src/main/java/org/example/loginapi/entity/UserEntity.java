package org.example.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "USERS")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String username;
    private String email;
    private String password;
}

package org.example.loginapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String username;
    private String password;
    @JsonIgnore
    private String role;
    @JsonIgnore
    private List<String> permissions;
}

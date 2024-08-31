package org.example.loginapi.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCredentials {
    @NotBlank(message = "username can't be left empty")
    @Schema(example = "username")
    private String username;

    @NotBlank(message = "Password can't be left empty")
    @Size(min = 8, message = "Password is too short")
    @Schema(example = "Password123!")
    private String password;
}


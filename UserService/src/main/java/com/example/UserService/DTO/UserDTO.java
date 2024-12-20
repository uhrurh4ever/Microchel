package com.example.UserService.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UserDTO(
        @Email @NotBlank
        String email,
        @NotBlank @Size(min = 8)
        String password,
        @NotBlank String name
) {
}

package com.quickserve.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email foramt")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=8,message = "Password lenght must be minimum of 8 characters")
    private String password;
}

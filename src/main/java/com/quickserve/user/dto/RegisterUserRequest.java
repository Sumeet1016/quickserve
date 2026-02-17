package com.quickserve.user.dto;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String email;
    private String password;
}

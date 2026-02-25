package com.quickserve.user.controller;

import com.quickserve.user.dto.AuthResponse;
import com.quickserve.user.dto.LoginRequest;
import com.quickserve.user.dto.RegisterUserRequest;
import com.quickserve.user.dto.UserResponse;
import com.quickserve.user.entity.User;
import com.quickserve.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterUserRequest request){
        return userService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request){
        return userService.login(request);
    }

    @GetMapping("/me")
    public String me(){
        return "me";
    }
}

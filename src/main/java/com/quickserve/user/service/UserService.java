package com.quickserve.user.service;

import com.quickserve.exception.EmailAlreadyExistsException;
import com.quickserve.exception.InvalidCredentialsException;
import com.quickserve.user.dto.LoginRequest;
import com.quickserve.user.dto.RegisterUserRequest;
import com.quickserve.user.dto.UserResponse;
import com.quickserve.user.entity.Role;
import com.quickserve.user.entity.User;
import com.quickserve.user.repsoitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.service.annotation.PutExchange;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
//Registration
    public UserResponse register(RegisterUserRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email already Exists");
        }

        User user=User.builder()
                .email(request.getEmail())
        .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        User saved=userRepository.save(user);

        return UserResponse.builder()
                .id(saved.getId())
                .email(saved.getEmail())
                .role(saved.getRole().name())
                .build();
    }
    //Login
    public UserResponse login(LoginRequest request){

        User user=userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new InvalidCredentialsException("Invalid email or password"));

        if(!passwordEncoder.matches(request.getPassword(),user.getPasswordHash())){
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return UserResponse.builder()
                .id(user.getId())
        .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}

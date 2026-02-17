package com.quickserve.user.service;

import com.quickserve.user.dto.RegisterUserRequest;
import com.quickserve.user.dto.UserResponse;
import com.quickserve.user.entity.Role;
import com.quickserve.user.entity.User;
import com.quickserve.user.repsoitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public UserResponse register(RegisterUserRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already Exists");
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
}

package com.gutenstobern.be_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gutenstobern.be_spring.dto.LoginRequestDTO;
import com.gutenstobern.be_spring.dto.LoginResponseDTO;
import com.gutenstobern.be_spring.dto.SignupRequestDTO;
import com.gutenstobern.be_spring.entity.User;
import com.gutenstobern.be_spring.entity.UserRole;
import com.gutenstobern.be_spring.repository.UserRepository;
import com.gutenstobern.be_spring.repository.UserRoleRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public LoginResponseDTO authenticateUser(LoginRequestDTO loginUser) {
        return userRepository.findByUsername(loginUser.getUsername())
                .filter(user -> passwordEncoder.matches(loginUser.getPassword(), user.getPassword()))
                .map(user -> new LoginResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        List.of(user.getRole().getName())))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Invalid credentials"));
    }

    public LoginResponseDTO registerUser(SignupRequestDTO signupUser) {
        String username = signupUser.getUsername();
        String password = passwordEncoder.encode(signupUser.getPassword());
        UserRole role = userRoleRepository.findByName("user")
                .orElseThrow(() -> new IllegalStateException("Default role 'user' not found"));

        User savedUser = userRepository.save(new User(username, password, role));

        return new LoginResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                List.of(savedUser.getRole().getName()));
    }
}

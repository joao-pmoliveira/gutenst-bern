package com.gutenstobern.be_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gutenstobern.be_spring.dto.LoginRequestDTO;
import com.gutenstobern.be_spring.dto.LoginResponseDTO;
import com.gutenstobern.be_spring.dto.SignupRequestDTO;
import com.gutenstobern.be_spring.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(userService.authenticateUser(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDTO> signupUser(@RequestBody SignupRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.registerUser(request));
    }
}

package com.ramzi.workshop.controllers;

import com.ramzi.workshop.dto.AuthResponseDto;
import com.ramzi.workshop.dto.LoginDto;
import com.ramzi.workshop.dto.RegisterDto;
import com.ramzi.workshop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")

public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        authService.register(registerDto);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }
}

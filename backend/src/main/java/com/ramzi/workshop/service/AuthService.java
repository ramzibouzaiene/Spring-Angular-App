package com.ramzi.workshop.service;

import com.ramzi.workshop.dto.AuthResponseDto;
import com.ramzi.workshop.dto.LoginDto;
import com.ramzi.workshop.dto.RegisterDto;

public interface AuthService {
    AuthResponseDto login(LoginDto loginDto);
    void register(RegisterDto registerDto);
}

package com.ramzi.workshop.service.impl;

import com.ramzi.workshop.auth.JwtTokenProvider;
import com.ramzi.workshop.dto.AuthResponseDto;
import com.ramzi.workshop.dto.LoginDto;
import com.ramzi.workshop.dto.RegisterDto;
import com.ramzi.workshop.enums.RoleEnum;
import com.ramzi.workshop.model.User;
import com.ramzi.workshop.repository.UserRepository;
import com.ramzi.workshop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDto login(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUserName(),
                loginDto.getPassword()
        ));

        var user = userRepository.findByUserName(loginDto.getUserName())
                .orElseThrow();
        var jwtToken = jwtTokenProvider.generateToken(user);
        return AuthResponseDto.builder()
                .accessToken(jwtToken)
                .build();
    }

    @Override
    public void register(RegisterDto registerDto) {
        var user = User.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .userName(registerDto.getUserName())
                .role(RoleEnum.USER)
                .build();
        userRepository.save(user);
    }
}

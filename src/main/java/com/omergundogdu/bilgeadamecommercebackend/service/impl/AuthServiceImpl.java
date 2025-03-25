package com.omergundogdu.bilgeadamecommercebackend.service.impl;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.LoginRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.request.RegisterRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.request.TokenRefreshRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.AuthResponse;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.TokenRefreshResponse;
import com.omergundogdu.bilgeadamecommercebackend.model.RefreshToken;
import com.omergundogdu.bilgeadamecommercebackend.model.Role;
import com.omergundogdu.bilgeadamecommercebackend.model.User;
import com.omergundogdu.bilgeadamecommercebackend.repository.RoleRepository;
import com.omergundogdu.bilgeadamecommercebackend.repository.UserRepository;
import com.omergundogdu.bilgeadamecommercebackend.security.JwtUtil;
import com.omergundogdu.bilgeadamecommercebackend.service.read.AuthReadableService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.AuthWriteableService;
import com.omergundogdu.bilgeadamecommercebackend.service.read.RefreshTokenReadableService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.RefreshTokenWriteableService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthReadableService, AuthWriteableService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenReadableService refreshTokenReadableService;
    private final RefreshTokenWriteableService refreshTokenWriteableService;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Bu email zaten kayıtlı");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER rolü bulunamadı"));

        return getAuthResponse(request, userRole);
    }

    @Override
    public AuthResponse adminRegister(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Bu email zaten kayıtlı");
        }

        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("ADMIN rolü bulunamadı"));

        return getAuthResponse(request, adminRole);
    }

    private AuthResponse getAuthResponse(RegisterRequest request, Role role) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .roles(Collections.singleton(role))
                .build();

        userRepository.save(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());

        String token = jwtUtil.generateToken(user.getEmail(), claims);

        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());

        String token = jwtUtil.generateToken(user.getEmail(), claims);

        return new AuthResponse(token);
    }


    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        RefreshToken refreshToken = refreshTokenReadableService.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Refresh token bulunamadı"));

        refreshTokenWriteableService.verifyExpiration(refreshToken);

        User user = refreshToken.getUser();

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());

        String newAccessToken = jwtUtil.generateToken(user.getEmail(), claims);

        return new TokenRefreshResponse(newAccessToken, refreshToken.getToken());
    }

}
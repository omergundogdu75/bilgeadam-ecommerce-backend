package com.omergundogdu.bilgeadamecommercebackend.controller;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.LoginRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.request.RegisterRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.request.TokenRefreshRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.AuthResponse;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.TokenRefreshResponse;

import com.omergundogdu.bilgeadamecommercebackend.service.read.AuthReadableService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.AuthWriteableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthWriteableService authWriteableService;
    private final AuthReadableService authReadableService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authWriteableService.register(request));
    }

    @PostMapping("/admin-register")
    public ResponseEntity<AuthResponse> adminRegister(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authWriteableService.adminRegister(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authWriteableService.login(request));
    }

    @PostMapping("/admin-login")
    public ResponseEntity<AuthResponse> adminEndpoint(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authWriteableService.adminLogin(request));
    }


    @PostMapping("/refresh")
    public ResponseEntity<TokenRefreshResponse> refresh(@RequestBody TokenRefreshRequest request) {
        return ResponseEntity.ok(authReadableService.refreshToken(request));
    }
}

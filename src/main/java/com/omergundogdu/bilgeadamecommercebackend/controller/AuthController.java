package com.omergundogdu.bilgeadamecommercebackend.controller;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.LoginRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.request.RegisterRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.request.TokenRefreshRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.AuthResponse;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.TokenRefreshResponse;
import com.omergundogdu.bilgeadamecommercebackend.service.read.AuthReadableService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.AuthWriteableService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Bu controller, kullanıcı ve admin kimlik doğrulama işlemlerini yönetir.
 * <p>
 * Kayıt, giriş, token yenileme gibi endpoint'leri içerir.
 * </p>
 *
 * @author  Ömer Gündoğdu</p>
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Auth API", description = "Kullanıcı kimlik doğrulama ve kayıt işlemleri")
public class AuthController {

    private final AuthWriteableService authWriteableService;
    private final AuthReadableService authReadableService;

    /**
     * Kullanıcı kaydı işlemi.
     *
     * @param request Kullanıcı kayıt isteği (email, şifre, fullName)
     * @return JWT token bilgisi
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) throws MessagingException {
        return ResponseEntity.ok(authWriteableService.register(request));
    }

    /**
     * Admin kaydı işlemi. (Sadece admin rolü ile kullanıcı eklemek için)
     *
     * @param request Admin kayıt isteği
     * @return JWT token bilgisi
     */
    @PostMapping("/admin-register")
    public ResponseEntity<AuthResponse> adminRegister(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authWriteableService.adminRegister(request));
    }

    /**
     * Kullanıcı giriş işlemi.
     *
     * @param request Email ve şifre bilgileri
     * @return JWT access token + refresh token bilgisi
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authWriteableService.login(request));
    }

    /**
     * Sadece admin kullanıcılar için giriş kontrolü yapar.
     * Giriş yapan kişinin rolü admin değilse hata fırlatır.
     *
     * @param request Giriş isteği
     * @return JWT access token
     */
    @PostMapping("/admin-login")
    public ResponseEntity<AuthResponse> adminEndpoint(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authWriteableService.adminLogin(request));
    }

    /**
     * Refresh token ile yeni access token üretir.
     *
     * @param request Refresh token içeren istek
     * @return Yeni access token
     */
    @PostMapping("/refresh")
    public ResponseEntity<TokenRefreshResponse> refresh(@RequestBody TokenRefreshRequest request) {
        return ResponseEntity.ok(authReadableService.refreshToken(request));
    }
}

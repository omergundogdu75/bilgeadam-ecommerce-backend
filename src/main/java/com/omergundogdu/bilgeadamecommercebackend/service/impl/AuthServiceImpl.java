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

/**
 * Kullanıcı kimlik doğrulama, kayıt, giriş ve refresh token işlemlerini yöneten servis sınıfı.
 * <p>
 * Bu servis, kullanıcı kaydı, giriş işlemleri, admin girişi, ve token yenileme gibi işlemleri içerir.
 * Kullanıcıların rollerine göre yetkilendirme yapılır ve güvenlik için JWT token kullanılır.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthReadableService, AuthWriteableService {

    /**
     * Kullanıcı veritabanı işlemleri için kullanılan repository.
     */
    private final UserRepository userRepository;

    /**
     * Rol veritabanı işlemleri için kullanılan repository.
     */
    private final RoleRepository roleRepository;

    /**
     * Şifrelerin şifrelenmesi için kullanılan şifreleyici.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Kullanıcı kimlik doğrulama işlemlerini yöneten AuthenticationManager.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Refresh token okuma işlemleri için kullanılan servis.
     */
    private final RefreshTokenReadableService refreshTokenReadableService;

    /**
     * Refresh token yazma işlemleri için kullanılan servis.
     */
    private final RefreshTokenWriteableService refreshTokenWriteableService;

    /**
     * JWT token işlemleri için kullanılan yardımcı sınıf.
     */
    private final JwtUtil jwtUtil;

    /**
     * Kullanıcı kaydını gerçekleştirir.
     * <p>
     * Eğer kullanıcı zaten kayıtlıysa bir hata fırlatılır. Kullanıcıya "USER" rolü atanarak kayıt işlemi yapılır.
     * </p>
     *
     * @param request Kayıt isteği.
     * @return Kullanıcı kaydı başarılı ise bir AuthResponse döner.
     */
    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Bu email zaten kayıtlı");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER rolü bulunamadı"));

        return getAuthResponse(request, userRole);
    }

    /**
     * Admin kaydını gerçekleştirir.
     * <p>
     * Eğer kullanıcı zaten kayıtlıysa bir hata fırlatılır. Kullanıcıya "ADMIN" rolü atanarak kayıt işlemi yapılır.
     * </p>
     *
     * @param request Kayıt isteği.
     * @return Admin kaydı başarılı ise bir AuthResponse döner.
     */
    @Override
    public AuthResponse adminRegister(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Bu email zaten kayıtlı");
        }

        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("ADMIN rolü bulunamadı"));

        return getAuthResponse(request, adminRole);
    }

    /**
     * Kullanıcı kaydını gerçekleştiren metod.
     * <p>
     * Verilen kullanıcı bilgileri ve rol ile kullanıcı kaydedilir ve JWT token üretilir.
     * </p>
     *
     * @param request Kayıt isteği.
     * @param role Kullanıcıya atanacak rol.
     * @return Başarılı kayıt sonrası AuthResponse döner.
     */
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
        claims.put("roles", user.getRoles().stream().map(Role::getName).toList());

        String token = jwtUtil.generateToken(user.getEmail(), claims);

        return new AuthResponse(token);
    }

    /**
     * Kullanıcı giriş işlemini gerçekleştirir.
     * <p>
     * Kullanıcı doğrulama işlemi yapılır ve JWT token üretilir.
     * </p>
     *
     * @param request Giriş isteği.
     * @return Başarılı giriş sonrası AuthResponse döner.
     */
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
        claims.put("roles", user.getRoles().stream().map(Role::getName).toList());

        String token = jwtUtil.generateToken(user.getEmail(), claims);

        return new AuthResponse(token);
    }

    /**
     * Admin giriş işlemini gerçekleştirir.
     * <p>
     * Kullanıcı doğrulama işlemi yapılır, admin rolü kontrol edilir ve JWT token üretilir.
     * </p>
     *
     * @param request Admin giriş isteği.
     * @return Başarılı giriş sonrası AuthResponse döner.
     */
    @Override
    public AuthResponse adminLogin(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        boolean isAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getName().equals("ADMIN"));

        if (!isAdmin) {
            throw new RuntimeException("Yetkisiz işlem: Admin yetkisine sahip değilsiniz.");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("roles", user.getRoles().stream().map(Role::getName).toList());

        String token = jwtUtil.generateToken(user.getEmail(), claims);

        return new AuthResponse(token);
    }

    /**
     * Refresh token ile yeni bir access token oluşturur.
     * <p>
     * Verilen refresh token doğrulanır ve geçerliliği onaylandıktan sonra yeni bir access token üretilir.
     * </p>
     *
     * @param request Refresh token yenileme isteği.
     * @return Yeni access token ve eski refresh token'ı içeren TokenRefreshResponse döner.
     */
    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        RefreshToken refreshToken = refreshTokenReadableService.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Refresh token bulunamadı"));

        refreshTokenWriteableService.verifyExpiration(refreshToken);

        User user = refreshToken.getUser();

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("roles", user.getRoles().stream().map(Role::getName).toList());

        String newAccessToken = jwtUtil.generateToken(user.getEmail(), claims);

        return new TokenRefreshResponse(newAccessToken, refreshToken.getToken());
    }

}

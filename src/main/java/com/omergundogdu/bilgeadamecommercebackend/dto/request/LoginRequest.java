package com.omergundogdu.bilgeadamecommercebackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kullanıcı giriş işlemleri için gerekli olan e-posta ve şifre bilgilerinin tutulduğu veri transfer nesnesidir (DTO).
 *
 * <p>
 * Bu sınıf, istemciden gelen kullanıcı giriş taleplerinde kullanılır.
 * Giriş doğrulaması yapılmadan önce gerekli olan e-posta ve şifre alanlarını içerir.
 * </p>
 *
 * <p><b>Kullanım:</b> /api/auth/login ve /api/auth/admin-login endpoint'lerinde kullanılır.</p>
 *
 * @author  Ömer Gündoğdu</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    /**
     * Kullanıcının e-posta adresi.
     * Giriş işlemlerinde kullanıcıyı tanımlamak için kullanılır.
     * Örnek: "omer@example.com"
     */
    private String email;

    /**
     * Kullanıcının şifresi.
     * E-posta adresiyle birlikte giriş işlemlerinde doğrulama için kullanılır.
     * Örnek: "123456"
     */
    private String password;
}

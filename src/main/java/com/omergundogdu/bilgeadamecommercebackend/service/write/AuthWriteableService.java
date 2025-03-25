package com.omergundogdu.bilgeadamecommercebackend.service.write;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.LoginRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.request.RegisterRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.AuthResponse;
import jakarta.mail.MessagingException;

/**
 * Kullanıcı kimlik doğrulama ve kayıt işlemleri için gerekli olan yazma (write) servis işlemlerini tanımlar.
 * <p>
 * Bu servis, kullanıcı kayıt işlemleri, giriş işlemleri ve admin giriş işlemleri gibi işlemleri sağlar.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
public interface AuthWriteableService {

    /**
     * Yeni bir kullanıcı kaydeder.
     * <p>
     * Bu metod, yeni bir kullanıcıyı kaydeder ve başarılı bir kayıt sonrası bir token ile dönüş yapar.
     * </p>
     *
     * @param request Kullanıcı kayıt isteği.
     * @return Kayıt işlemi sonrası döndürülen {@link AuthResponse} nesnesi.
     */
    AuthResponse register(RegisterRequest request) throws MessagingException;

    /**
     * Admin kaydeder.
     * <p>
     * Bu metod, admin rolüne sahip bir kullanıcıyı kaydeder ve başarılı bir kayıt sonrası bir token ile dönüş yapar.
     * </p>
     *
     * @param request Admin kullanıcı kayıt isteği.
     * @return Kayıt işlemi sonrası döndürülen {@link AuthResponse} nesnesi.
     */
    AuthResponse adminRegister(RegisterRequest request);

    /**
     * Kullanıcı giriş işlemi gerçekleştirir.
     * <p>
     * Bu metod, kullanıcının giriş bilgilerini alır ve doğrulama işlemi sonrası bir token ile dönüş yapar.
     * </p>
     *
     * @param request Kullanıcı giriş isteği.
     * @return Başarılı giriş sonrası döndürülen {@link AuthResponse} nesnesi.
     */
    AuthResponse login(LoginRequest request);

    /**
     * Admin giriş işlemi gerçekleştirir.
     * <p>
     * Bu metod, admin kullanıcısının giriş bilgilerini alır ve doğrulama işlemi sonrası bir token ile dönüş yapar.
     * </p>
     *
     * @param request Admin giriş isteği.
     * @return Başarılı giriş sonrası döndürülen {@link AuthResponse} nesnesi.
     */
    AuthResponse adminLogin(LoginRequest request);
}

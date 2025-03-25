package com.omergundogdu.bilgeadamecommercebackend.service.read;

import com.omergundogdu.bilgeadamecommercebackend.model.RefreshToken;

import java.util.Optional;

/**
 * Refresh token okuma işlemlerini tanımlayan servis arayüzüdür.
 * Token'ın doğrulanması ve token ile veritabanından sorgulama işlemlerini içerir.
 *
 * @author Ömer GÜNDOĞDU
 */
public interface RefreshTokenReadableService {

    /**
     * Verilen token string’ine göre refresh token’ı veritabanından bulur.
     *
     * @param token Aranacak token string’i
     * @return Opsiyonel olarak RefreshToken nesnesi
     */
    Optional<RefreshToken> findByToken(String token);

    /**
     * Refresh token’ın süresinin geçip geçmediğini kontrol eder.
     * Süresi dolmuşsa token silinir ve hata fırlatılır.
     *
     * @param token Doğrulanacak refresh token
     * @return Geçerli olan RefreshToken nesnesi
     * @throws RuntimeException Eğer token süresi dolmuşsa
     */
    RefreshToken verifyExpiration(RefreshToken token);
}

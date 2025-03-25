package com.omergundogdu.bilgeadamecommercebackend.service.read;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.TokenRefreshRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.TokenRefreshResponse;

/**
 * Kullanıcı kimlik doğrulama ve token yenileme işlemleri için gerekli olan okuma (read) servis işlemlerini tanımlar.
 * <p>
 * Bu servis, token yenileme işlemi gibi kullanıcı doğrulama ile ilgili işlemleri gerçekleştirir.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
public interface AuthReadableService {

    /**
     * Verilen refresh token ile yeni bir access token üretir.
     * <p>
     * Bu metod, geçerli bir refresh token kullanarak yeni bir access token oluşturur.
     * </p>
     *
     * @param request Refresh token yenileme isteği.
     * @return Yeni access token'ı içeren {@link TokenRefreshResponse} döner.
     */
    TokenRefreshResponse refreshToken(TokenRefreshRequest request);
}

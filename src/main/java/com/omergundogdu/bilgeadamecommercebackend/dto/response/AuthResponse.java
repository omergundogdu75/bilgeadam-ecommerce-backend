package com.omergundogdu.bilgeadamecommercebackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kimlik doğrulama işlemi (login, register vb.) sonucunda
 * kullanıcıya dönen access token'ı temsil eden response sınıfıdır.
 *
 * <p><b>Örnek Yanıt:</b></p>
 * <pre>{@code
 * {
 *   "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
 *   "tokenType": "Bearer"
 * }
 * }</pre>
 *
 * <p>Varsayılan olarak token tipi "Bearer" olarak belirlenmiştir.</p>
 *
 * <p><b>Kullanım:</b> Authorization header'ında
 * {@code Authorization: Bearer {accessToken}} şeklinde kullanılır.</p>
 *
 * @author  Ömer Gündoğdu</p>
 */
@Data
public class AuthResponse {

    /**
     * Kullanıcıya verilen JWT access token.
     */
    private String accessToken;

    /**
     * Token tipi. Genellikle "Bearer" olur.
     */
    private String tokenType = "Bearer";

    /**
     * Access token'ı alarak response objesi oluşturan constructor.
     *
     * @param accessToken JWT access token
     */
    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}

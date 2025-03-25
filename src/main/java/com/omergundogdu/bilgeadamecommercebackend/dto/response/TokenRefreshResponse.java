package com.omergundogdu.bilgeadamecommercebackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JWT token yenileme yanıtını temsil eden response sınıfıdır.
 *
 * <p>Bu sınıf, kullanıcıların refresh token kullanarak yeni bir access token almasını sağlayan
 * bir yanıt (response) nesnesidir. Yenilenmiş access token ve refresh token'lar
 * bu sınıfın içinde gönderilir.</p>
 *
 * <p><b>Alanlar:</b></p>
 * <ul>
 *   <li><b>accessToken</b> - Yenilenmiş access token, kullanıcının kimliğini doğrulamak için kullanılır.</li>
 *   <li><b>refreshToken</b> - Yenilenmiş refresh token, access token'ın süresi bittiğinde yeni bir token almak için kullanılır.</li>
 *   <li><b>tokenType</b> - Token türü, genellikle "Bearer" olarak kullanılır.</li>
 * </ul>
 *
 * <p><b>Kullanım Örneği (JSON):</b></p>
 * <pre>{@code
 * {
 *   "accessToken": "newAccessTokenString",
 *   "refreshToken": "newRefreshTokenString",
 *   "tokenType": "Bearer"
 * }
 * }</pre>
 *
 * @author  Ömer Gündoğdu</p>
 */
@Data
public class TokenRefreshResponse {

    /**
     * Yenilenmiş access token.
     */
    private String accessToken;

    /**
     * Yenilenmiş refresh token.
     */
    private String refreshToken;

    /**
     * Token türü (varsayılan: Bearer).
     */
    private String tokenType = "Bearer";

    /**
     * Parametreli constructor, access token ve refresh token almak için kullanılır.
     *
     * @param accessToken Yenilenmiş access token.
     * @param refreshToken Yenilenmiş refresh token.
     */
    public TokenRefreshResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}

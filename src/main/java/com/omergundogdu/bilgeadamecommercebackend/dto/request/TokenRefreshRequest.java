package com.omergundogdu.bilgeadamecommercebackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Refresh token kullanılarak yeni bir access token oluşturmak için kullanılan request sınıfıdır.
 *
 * <p>JWT access token süresi dolduğunda, client tarafından
 * sunucuya gönderilen refresh token bu sınıfla taşınır.</p>
 *
 * <p><b>Endpoint:</b> POST /api/auth/refresh</p>
 *
 * <p><b>Kullanım:</b> Refresh token doğrulanırsa backend tarafından yeni access token döner.</p>
 *
 * @author  Ömer Gündoğdu</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenRefreshRequest {

    /**
     * Kullanıcının sahip olduğu geçerli refresh token.
     */
    private String refreshToken;
}

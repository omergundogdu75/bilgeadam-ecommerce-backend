package com.omergundogdu.bilgeadamecommercebackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kullanıcı kayıt (register) işlemleri için kullanılan DTO sınıfıdır.
 *
 * <p>Yeni bir kullanıcı sistemde hesap oluşturmak istediğinde,
 * bu sınıf içerisindeki bilgiler backend'e gönderilir.</p>
 *
 * <p><b>Endpoint:</b> POST /api/auth/register</p>
 *
 * @author  Ömer Gündoğdu</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    /**
     * Kullanıcının e-posta adresi.
     */
    private String email;

    /**
     * Kullanıcının belirlediği şifre.
     */
    private String password;

    /**
     * Kullanıcının tam adı.
     */
    private String fullName;
}

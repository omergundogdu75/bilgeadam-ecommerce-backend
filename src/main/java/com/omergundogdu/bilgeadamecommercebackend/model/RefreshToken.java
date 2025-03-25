package com.omergundogdu.bilgeadamecommercebackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * E-ticaret sistemindeki kullanıcıların refresh token bilgilerini temsil eden entity sınıfı.
 * <p>
 * Bu sınıf, kullanıcıların kimlik doğrulama işlemi için kullanılan refresh token'larını içerir ve "refresh_tokens" tablosuna karşılık gelir.
 * </p>
 *
 * <p>
 * Refresh token, kullanıcıların mevcut oturumlarını yenileyebilmesi için kullanılan bir anahtardır ve bu sınıf, token'ın kendisi,
 * hangi kullanıcıya ait olduğu ve token'ın geçerlilik süresi bilgilerini tutar.
 * </p>
 *
 * <p>
 * Bu sınıf, Lombok anotasyonlarını kullanarak getter, setter, constructor ve builder gibi
 * gereksiz kod tekrarını azaltmaktadır.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
@Entity
@Table(name = "refresh_tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken {

    /**
     * Refresh token'ın benzersiz tanımlayıcısı.
     * Bu alan, veritabanı tarafından otomatik olarak üretilir.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Refresh token'ın kendisi.
     * Bu alan boş olamaz ve her kullanıcıya özel bir token içerir.
     */
    @Column(nullable = false, unique = true)
    private String token;

    /**
     * Refresh token'ın ait olduğu kullanıcı.
     * Bu ilişki {@link User} sınıfıyla bir'e bir (OneToOne) ilişki şeklinde tanımlanmıştır.
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Refresh token'ın geçerlilik süresi.
     * Bu alan, token'ın ne zaman süresinin dolacağını belirtir.
     */
    @Column(nullable = false)
    private Instant expiryDate;
}

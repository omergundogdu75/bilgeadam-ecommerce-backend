package com.omergundogdu.bilgeadamecommercebackend.repository;

import com.omergundogdu.bilgeadamecommercebackend.model.RefreshToken;
import com.omergundogdu.bilgeadamecommercebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Refresh token veritabanı işlemleri için kullanılan repository arayüzü.
 * <p>
 * Bu arayüz, {@link RefreshToken} modeline yönelik temel CRUD (Create, Read, Update, Delete) işlemleri sağlar.
 * Ayrıca, refresh token ile ilgili bazı özel sorgu yöntemlerini içerir.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    /**
     * Verilen token değerine sahip bir RefreshToken'ı döner.
     * <p>
     * Bu metod, belirli bir refresh token'ı bulmak için kullanılır.
     * </p>
     *
     * @param token Refresh token değeri.
     * @return Verilen token ile eşleşen RefreshToken, bulunamazsa boş döner.
     */
    Optional<RefreshToken> findByToken(String token);

    /**
     * Verilen kullanıcıya ait tüm refresh token'ları siler.
     * <p>
     * Bu metod, bir kullanıcının tüm refresh token'larını veritabanından silmek için kullanılır.
     * </p>
     *
     * @param user Silinecek refresh token'lara ait kullanıcı.
     */
    void deleteByUser(User user);
}

package com.omergundogdu.bilgeadamecommercebackend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT (JSON Web Token) işlemlerini gerçekleştiren yardımcı sınıf.
 * <p>
 * Bu sınıf, JWT oluşturma, token doğrulama, token içeriğinden veri çıkarma gibi işlemleri gerçekleştirir.
 * Kullanıcı kimliği doğrulama ve yetkilendirme için JWT tabanlı bir güvenlik mekanizması sağlar.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
@Component
public class JwtUtil {

    /**
     * JWT'nin imzalanmasında kullanılan gizli anahtar.
     */
    private final Key key;

    /**
     * Token'ın geçerlilik süresi (24 saat).
     */
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 saat

    /**
     * JWT işlemleri için kullanılan yardımcı sınıfın yapıcısı.
     * <p>
     * Gizli anahtar, application.properties dosyasından alınır ve HMAC SHA algoritması ile bir anahtar oluşturulur.
     * </p>
     *
     * @param secret JWT için kullanılan gizli anahtar.
     */
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Yeni bir JWT token oluşturur.
     *
     * @param subject Token'ın konusu (genellikle kullanıcı e-posta adresi).
     * @param claims Token'a eklenmesi gereken ek bilgiler (claimler).
     * @return Oluşturulan JWT token'ı.
     */
    public String generateToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    /**
     * JWT token'ından kullanıcı ID'sini çıkarır.
     *
     * @param token JWT token'ı.
     * @return Kullanıcı ID'si.
     */
    public Long extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("userId", Long.class);
    }

    /**
     * JWT token'ından kullanıcı e-posta adresini (username) çıkarır.
     *
     * @param token JWT token'ı.
     * @return Kullanıcı e-posta adresi.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * JWT token'ından kullanıcı rollerini çıkarır.
     *
     * @param token JWT token'ı.
     * @return Kullanıcının rollerini içeren bir liste.
     */
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("roles", List.class);
    }

    /**
     * JWT token'ından her türlü claim'i çıkarır.
     *
     * @param token JWT token'ı.
     * @param resolver Claim'i almak için kullanılan fonksiyon.
     * @param <T> Claim türü.
     * @return İlgili claim değeri.
     */
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        final Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    /**
     * JWT token'ının geçerli olup olmadığını kontrol eder.
     *
     * @param token JWT token'ı.
     * @param email Kullanıcı e-posta adresi.
     * @return Geçerli ise true, değilse false döner.
     */
    public boolean isTokenValid(String token, String email) {
        final String username = extractUsername(token);
        return (username.equals(email) && !isTokenExpired(token));
    }

    /**
     * JWT token'ının süresinin dolup dolmadığını kontrol eder.
     *
     * @param token JWT token'ı.
     * @return Eğer token süresi dolmuşsa true, dolmamışsa false döner.
     */
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    /**
     * JWT token'ından tüm claim'leri çıkarır.
     *
     * @param token JWT token'ı.
     * @return JWT token'ının claim'leri.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

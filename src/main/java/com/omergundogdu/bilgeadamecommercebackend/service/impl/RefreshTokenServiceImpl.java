package com.omergundogdu.bilgeadamecommercebackend.service.impl;

import com.omergundogdu.bilgeadamecommercebackend.model.User;
import com.omergundogdu.bilgeadamecommercebackend.model.RefreshToken;
import com.omergundogdu.bilgeadamecommercebackend.repository.RefreshTokenRepository;
import com.omergundogdu.bilgeadamecommercebackend.service.read.RefreshTokenReadableService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.RefreshTokenWriteableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * Refresh token işlemlerinin gerçekleştirildiği servis sınıfıdır.
 * Token oluşturma, doğrulama, silme ve veritabanından token çekme işlemlerini kapsar.
 *
 * @author Ömer GÜNDOĞDU
 */
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenReadableService, RefreshTokenWriteableService {

    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * Belirtilen kullanıcı için yeni bir refresh token oluşturur.
     * Önceki token varsa silinir ve yerine yenisi oluşturulur.
     *
     * @param user Refresh token oluşturulacak kullanıcı
     * @return Oluşturulan RefreshToken nesnesi
     */
    @Override
    public RefreshToken createRefreshToken(User user) {
        refreshTokenRepository.deleteByUser(user); // önceki refresh token'ı sil

        return refreshTokenRepository.save(RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusSeconds(60 * 60 * 24 * 7)) // 7 gün geçerli
                .build());
    }

    /**
     * Verilen refresh token'ın süresinin geçip geçmediğini kontrol eder.
     * Süresi geçmişse token silinir ve RuntimeException fırlatılır.
     *
     * @param token Doğrulanacak refresh token
     * @return Geçerli refresh token
     * @throws RuntimeException Eğer token süresi dolmuşsa
     */
    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token süresi dolmuş");
        }
        return token;
    }

    /**
     * Belirtilen kullanıcıya ait refresh token'ı siler.
     *
     * @param user Token'ı silinecek kullanıcı
     */
    @Override
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }

    /**
     * Verilen token string’ine göre refresh token’ı veritabanından bulur.
     *
     * @param token Aranacak token string’i
     * @return Opsiyonel olarak RefreshToken
     */
    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
}

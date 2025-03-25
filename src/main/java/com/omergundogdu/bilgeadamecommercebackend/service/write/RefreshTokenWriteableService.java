package com.omergundogdu.bilgeadamecommercebackend.service.write;

import com.omergundogdu.bilgeadamecommercebackend.model.RefreshToken;
import com.omergundogdu.bilgeadamecommercebackend.model.User;

/**
 * Refresh token yazma işlemlerini tanımlayan servis arayüzüdür.
 * Yeni token oluşturma, kullanıcıya ait token’ı silme ve token süresi doğrulama işlemlerini içerir.
 * Bu servis genellikle kimlik doğrulama sürecinin bir parçası olarak kullanılır.
 *
 * @author Ömer GÜNDOĞDU
 */
public interface RefreshTokenWriteableService {

    /**
     * Belirtilen kullanıcı için yeni bir refresh token oluşturur.
     * Var olan eski token varsa silinir.
     *
     * @param user Refresh token oluşturulacak kullanıcı
     * @return Oluşturulan RefreshToken nesnesi
     */
    RefreshToken createRefreshToken(User user);

    /**
     * Belirtilen kullanıcıya ait refresh token'ı siler.
     *
     * @param user Token'ı silinecek kullanıcı
     */
    void deleteByUser(User user);

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

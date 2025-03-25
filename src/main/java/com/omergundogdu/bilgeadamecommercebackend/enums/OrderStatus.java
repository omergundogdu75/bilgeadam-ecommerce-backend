package com.omergundogdu.bilgeadamecommercebackend.enums;

/**
 * Sistemdeki bir siparişin olası durumlarını temsil eden enum.
 * <p>
 * Olası durumlar şunlardır:
 * <ul>
 *     <li>{@link #PENDING} - Sipariş beklemede olup tamamlanmamış veya iptal edilmemiştir.</li>
 *     <li>{@link #COMPLETED} - Sipariş başarıyla tamamlanmıştır.</li>
 *     <li>{@link #CANCELLED} - Sipariş kullanıcı veya sistem tarafından iptal edilmiştir.</li>
 * </ul>
 * </p>
 * <p>
 * Bu enum, bir siparişin yaşam döngüsü boyunca durumunu takip etmek ve güncellemek için kullanılır.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
public enum OrderStatus {
    /**
     * Sipariş beklemede olup işleme alınmayı bekliyor.
     */
    PENDING,

    /**
     * Sipariş başarıyla tamamlanmıştır.
     */
    COMPLETED,

    /**
     * Sipariş iptal edilmiştir.
     */
    CANCELLED
}

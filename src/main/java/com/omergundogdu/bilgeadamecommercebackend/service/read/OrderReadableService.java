package com.omergundogdu.bilgeadamecommercebackend.service.read;

import com.omergundogdu.bilgeadamecommercebackend.model.Order;

import java.util.List;

/**
 * Sipariş ile ilgili okuma (read) servis işlemlerini tanımlar.
 * <p>
 * Bu servis, siparişlerin alınması ve kullanıcıya ait veya tüm siparişlerin listelenmesi gibi işlemleri sağlar.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
public interface OrderReadableService {

    /**
     * Verilen ID'ye sahip siparişi getirir.
     * <p>
     * Bu metod, belirli bir siparişi almak için kullanılır.
     * Eğer sipariş bulunamazsa bir hata fırlatılır.
     * </p>
     *
     * @param id Siparişin benzersiz ID'si.
     * @return İlgili siparişi içeren {@link Order} nesnesi.
     */
    Order getById(Long id);

    /**
     * Verilen kullanıcı ID'sine ait tüm siparişleri getirir.
     * <p>
     * Bu metod, bir kullanıcının tüm siparişlerini almak için kullanılır.
     * </p>
     *
     * @param userId Kullanıcının benzersiz ID'si.
     * @return Kullanıcıya ait tüm siparişlerin listesini döner.
     */
    List<Order> getAllByUserId(Long userId);

    /**
     * Tüm siparişleri getirir.
     * <p>
     * Bu metod, admin gibi özel kullanıcılar için tüm siparişlerin listesini döndürür.
     * </p>
     *
     * @return Tüm siparişlerin listesini döner.
     */
    List<Order> getAll(); // opsiyonel: admin için
}

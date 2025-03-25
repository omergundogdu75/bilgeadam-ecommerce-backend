package com.omergundogdu.bilgeadamecommercebackend.repository;

import com.omergundogdu.bilgeadamecommercebackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Sipariş veritabanı işlemleri için kullanılan repository arayüzü.
 * <p>
 * Bu arayüz, {@link Order} modeline yönelik temel CRUD (Create, Read, Update, Delete) işlemleri sağlar.
 * Ayrıca, kullanıcıya ait siparişleri sorgulamak için bazı özel sorgu yöntemlerini içerir.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Verilen kullanıcı ID'sine ait tüm siparişleri döner.
     * <p>
     * Bu metod, bir kullanıcının tüm siparişlerini almak için kullanılır.
     * </p>
     *
     * @param userId Kullanıcı ID'si.
     * @return Kullanıcıya ait tüm siparişlerin listesini döner.
     */
    List<Order> findByUserId(Long userId);
}

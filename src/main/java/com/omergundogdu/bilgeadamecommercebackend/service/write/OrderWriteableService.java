package com.omergundogdu.bilgeadamecommercebackend.service.write;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.OrderRequest;

/**
 * Sipariş ile ilgili yazma (write) servis işlemlerini tanımlar.
 * <p>
 * Bu servis, yeni bir sipariş oluşturma ve mevcut bir siparişi iptal etme işlemlerini sağlar.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
public interface OrderWriteableService {

    /**
     * Yeni bir sipariş oluşturur.
     * <p>
     * Bu metod, kullanıcı tarafından yapılan bir siparişi alır ve siparişin oluşturulmasını sağlar.
     * Sipariş oluşturulmadan önce ürünlerin stok durumu kontrol edilir ve stoklar güncellenir.
     * </p>
     *
     * @param request Sipariş oluşturma isteği.
     * @param userId Siparişi veren kullanıcının ID'si.
     */
    void createOrder(OrderRequest request, Long userId);

    /**
     * Verilen ID'ye sahip siparişi iptal eder.
     * <p>
     * Bu metod, bir siparişi iptal eder ve siparişin durumunu "CANCELLED" olarak günceller.
     * </p>
     *
     * @param id İptal edilecek siparişin ID'si.
     */
    void cancelOrder(Long id);
}

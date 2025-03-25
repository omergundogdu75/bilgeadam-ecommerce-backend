package com.omergundogdu.bilgeadamecommercebackend.service.read;

import com.omergundogdu.bilgeadamecommercebackend.dto.response.ProductResponse;

import java.util.List;

/**
 * Ürün verilerini okuma işlemlerini gerçekleştiren servis arayüzüdür.
 * <p>
 * Bu arayüz, ürünlerin detaylarını, tüm ürün listesini
 * ve belirli bir kategoriye ait ürünleri getirme işlemlerini tanımlar.
 * <p>
 * Geliştirici: Ömer GÜNDOĞDU
 */
public interface ProductReadService {

    /**
     * Belirtilen ID'ye sahip ürünü döner.
     *
     * @param id Ürün ID'si
     * @return Ürün bilgisi
     */
    ProductResponse getProductById(Long id);

    /**
     * Tüm ürünlerin listesini döner.
     *
     * @return Ürün listesi
     */
    List<ProductResponse> getAllProducts();

    /**
     * Belirtilen kategori ID'sine ait ürünlerin listesini döner.
     *
     * @param categoryId Kategori ID'si
     * @return Ürün listesi
     */
    List<ProductResponse> getProductsByCategory(Long categoryId);
}

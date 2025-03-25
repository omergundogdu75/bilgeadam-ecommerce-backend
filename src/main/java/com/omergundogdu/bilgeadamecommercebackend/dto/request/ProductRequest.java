package com.omergundogdu.bilgeadamecommercebackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ürün oluşturma veya güncelleme işlemleri için kullanılan DTO (Data Transfer Object) sınıfıdır.
 *
 * <p>Bu sınıf, admin panelinde ürün eklerken veya düzenlerken kullanılmak üzere backend'e gönderilen
 * bilgileri içerir.</p>
 *
 * <p><b>Kullanım:</b> /api/products endpoint'lerine POST veya PUT istekleriyle gönderilir.</p>
 *
 * @author  Ömer Gündoğdu</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    /**
     * Ürün adı.
     */
    private String name;

    /**
     * Ürün açıklaması (detaylı bilgi).
     */
    private String description;

    /**
     * Ürünün fiyatı (₺ cinsinden).
     */
    private Double price;

    /**
     * Ürünün mevcut stok adedi.
     */
    private Integer stock;

    /**
     * Ürünün görsel bağlantısı (URL).
     */
    private String imageUrl;

    /**
     * Ürünün ait olduğu kategori ID'si.
     * Sadece ID değeri gönderilir.
     */
    private Long categoryId;

    /**
     * Ürünün ait olduğu marka ID'si.
     * Sadece ID değeri gönderilir.
     */
    private Long brandId;
}

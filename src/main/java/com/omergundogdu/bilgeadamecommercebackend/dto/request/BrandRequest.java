package com.omergundogdu.bilgeadamecommercebackend.dto.request;

import lombok.*;

/**
 * Marka (Brand) oluşturma veya güncelleme işlemlerinde kullanılan veri transfer nesnesidir.
 *
 * <p>
 * Bu sınıf, istemciden gelen marka adı ve açıklama bilgilerini taşımak için kullanılır.
 * </p>
 *
 * <p><b>Kullanım:</b> /api/brands endpoint'leriyle birlikte kullanılır.</p>
 *
 * @author Ömer Gündoğdu</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandRequest {

    /**
     * Markanın adı.
     * Örnek: "Apple", "Samsung", "Nike"
     */
    private String name;

    /**
     * Markaya ait açıklama bilgisi.
     * Örnek: "Teknoloji ürünleri üreticisi", "Spor giyim markası"
     */
    private String description;
}
